package org.teilen.server.engine;

import org.teilen.common.packet.Packet;
import org.teilen.common.packet.comm.Request;
import org.teilen.common.packet.comm.Response;
import org.teilen.common.packet.comm.Status;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.MetaType;
import org.teilen.server.domain.ClientSocket;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.util.LogUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IOEngine extends Thread {
    private static final int threadSleep = 200; //millis
    private static final int packetNumber = 5;
    private static final List<ClientSocket> clientSockets = new CopyOnWriteArrayList<>();
    private DiscoveryEngine discoveryEngine;
    private Boolean discoveryEngineFlag = false;

    public IOEngine() {
    }

    private static void removeClientSocket(ClientSocket clientSocket) {
        if (clientSocket != null) {
            clientSocket.close();
            ClientPacket clientPacket = new ClientPacket(-1, 0, MetaType.USER, clientSocket.getClientId(), ClientOp.CLIENT_DELETE);
            PacketQueue.writeIn(clientPacket);
        }
    }

    public static void removeClientSockets(List<Integer> clientSocketIndex) {
        //Remove client sockets with issues
        for (Integer idx : clientSocketIndex) {
            ClientSocket clientSocket = clientSockets.get(idx);
            clientSockets.remove(idx.intValue());
            removeClientSocket(clientSocket);
        }
    }

    public static void addClientSocket(ClientSocket clientSocket) throws IOException {
        if (clientSocket != null) {
            clientSockets.add(clientSocket);
            ClientPacket clientPacket = new ClientPacket(-1, 0, MetaType.USER, clientSocket.getClientId(), ClientOp.CLIENT_CREATE);
            PacketQueue.writeIn(clientPacket);
            LogUtil.info("Socket accepted : " + clientSocket.getSocket().toString());

        }
    }

    public static void removeAllClientSockets() {
        if (clientSockets.size() != 0) {
            for (ClientSocket clientSocket : clientSockets) {
                try {
                    clientSocket.close();
                    LogUtil.warn("" + clientSocket + " closed.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            clientSockets.removeAll(clientSockets);
        }
    }

    public void run() {
        while (true) {
            try {
                while (discoveryEngineFlag) {
                    if (discoveryEngine == null) {
                        this.discoveryEngine = new DiscoveryEngine();
                        this.discoveryEngine.start();
                    }

                    LogUtil.info("IOE: started.");
                    int totalClients = clientSockets.size();

                    List<Integer> removeClientsIndex = new ArrayList<>();
                    for (int i = 0; i < totalClients; i++) {
                        try {
                            ClientSocket clientSocket = clientSockets.get(i);
                            Integer clientId = clientSocket.getClientId();
                            ObjectOutputStream out = clientSocket.getOut();
                            ObjectInputStream in = clientSocket.getIn();

                            List<Packet> serverPackets = PacketQueue.readOut(clientId, packetNumber);
                            LogUtil.info("Server : packets " + serverPackets);
                            int serverPacketsNr = serverPackets != null ? serverPackets.size() : 0;
                            out.writeObject(new Request(0, serverPacketsNr));
                            out.flush();

                            Object inObject = in.readObject();
                            while (inObject == null) {
                                inObject = in.readObject();
                            }
                            if (inObject instanceof Response) {
                                Response response = (Response) inObject;
                                if (response.getStatus().name().equals(Status.OK.name())) {
                                    for (int j = 0; j < serverPacketsNr; j++) {
                                        Packet packet = serverPackets.get(j);
                                        out.writeObject(packet);
                                        out.flush();
                                        Object packetObject = in.readObject();
                                        while (packetObject == null) {
                                            packetObject = in.readObject();
                                        }
                                        Response packetResponse = null;
                                        if (packetObject instanceof Response) {
                                            packetResponse = (Response) packetObject;
                                            LogUtil.info("Server : - " + j + " sent packet " + packet + ", client-response " + packetResponse);
                                        }
                                        if (!packetResponse.getStatus().name().equals(Status.OK.name())) {
                                            LogUtil.info("Server : stopped sending packets , client-response " + packetResponse);
                                            break;
                                        }
                                    }
                                }
                            } else
                                inObject = null;


                            //Receiving from client to server
                            inObject = in.readObject();
                            while (inObject == null) {
                                inObject = in.readObject();
                            }
                            if (inObject instanceof Request) {
                                Request request = (Request) inObject;
                                List<Packet> clientPackets = new ArrayList<>();
                                Response response = new Response(0, Status.OK);
                                out.writeObject(response);
                                out.flush();
                                LogUtil.info("Server : acceptance response for client sent " + response);

                                for (int k = 0; k < request.getPackets(); k++) {
                                    Object inPacket = in.readObject();
                                    while (inPacket == null) {
                                        inPacket = in.readObject();
                                    }
                                    Packet packet = (Packet) inPacket;
                                    out.writeObject(response);
                                    out.flush();
                                    clientPackets.add(packet);
                                    LogUtil.info("Server : - " + k + " received from client " + packet + ", server-response " + response);
                                }
                                PacketQueue.writeIn(clientPackets);
                            }

                            out.close();
                            in.close();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            removeClientsIndex.add(i);
                        } catch (SocketException se) {
                            se.printStackTrace();
                            removeClientsIndex.add(i);

                        } catch (NullPointerException npe) {
                            npe.printStackTrace();
                            removeClientsIndex.add(i);
                        } catch (IOException io) {
                            io.printStackTrace();
                            removeClientsIndex.add(i);
                        }

                    }

                    removeClientSockets(removeClientsIndex);

                    LogUtil.info("IOE: finished.");
                    Thread.sleep(threadSleep);
                }
                LogUtil.info("--> IOE: waiting.");
                Thread.sleep(threadSleep);
                if (discoveryEngine != null) {
                    this.discoveryEngine.shutdown();
                    this.discoveryEngine = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void startServer() throws IOException {
        if (this.discoveryEngineFlag == false) {
            this.discoveryEngineFlag = true;
        }
    }

    public synchronized void shutdownServer() throws IOException {
        if (this.discoveryEngineFlag == true) {
            this.discoveryEngineFlag = false;
        }
    }

    class DiscoveryEngine extends Thread {
        private final int serverSocketPort = 8888;
        private final int serverSocketTimeout = 1500;
        private int counter = 0;
        private Boolean runFlag = null;
        private ServerSocket serverSocket;

        public DiscoveryEngine() {
        }

        @Override
        public void run() {
            try {
                this.serverSocket = new ServerSocket(serverSocketPort);
                this.serverSocket.setSoTimeout(serverSocketTimeout);
                this.runFlag = true;
                LogUtil.info("Socket server started.Port " + serverSocketPort + ", timeout " + serverSocketTimeout);
                while (runFlag) {
                    try {
                        Socket socket = serverSocket.accept();
                        counter++;
                        addClientSocket(new ClientSocket(counter, socket));

                    } catch (SocketTimeoutException ste) {
                        LogUtil.warn("Socket server timeout " + serverSocketTimeout + " reached.");
                    } catch (SocketException se) {
                        LogUtil.warn("Socket server closed.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(4000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public synchronized void shutdown() {
            try {
                runFlag = false;
                removeAllClientSockets();
                try {
                    if (serverSocket != null) {
                        serverSocket.close();
                        serverSocket = null;
                        LogUtil.warn("" + serverSocket + " closed.");
                    }
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                LogUtil.error(e.getMessage());
            }
        }
    }


}
