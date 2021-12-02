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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IOEngine extends Thread {
    private static final int threadSleep = 200; //millis
    private static final int packetNumber = 5;
    public List<ClientSocket> clientSockets;

    public IOEngine() {
        this.clientSockets = new CopyOnWriteArrayList<>();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("IOE: started.");
                int totalClients = clientSockets.size();
                List<Integer> removeClientsIndex = new ArrayList<>();
                for (int i = 0; i < totalClients; i++) {
                    try {
                        ClientSocket clientSocket = clientSockets.get(i);
                        Integer clientId = clientSocket.getClientId();
                        ObjectOutputStream out = clientSocket.getOut();
                        ObjectInputStream in = clientSocket.getIn();

                        List<Packet> serverPackets = PacketQueue.readOut(clientId, 5);
                        System.out.println("Server : packets " + serverPackets);
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
                                        System.out.println("Server : - " + j + " sent packet " + packet + ", client-response " + packetResponse);
                                    }
                                    if (!packetResponse.getStatus().name().equals(Status.OK.name())) {
                                        System.out.println("Server : stopped sending packets , client-response " + packetResponse);
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
                            System.out.println("Server : acceptance response for client sent " + response);

                            for (int k = 0; k < request.getPackets(); k++) {
                                Object inPacket = in.readObject();
                                while (inPacket == null) {
                                    inPacket = in.readObject();
                                }
                                Packet packet = (Packet) inPacket;
                                out.writeObject(response);
                                out.flush();
                                clientPackets.add(packet);
                                System.out.println("Server : - " + k + " received from client " + packet + ", server-response " + response);
                            }
                            PacketQueue.writeIn(clientPackets);
                        }

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

                //Remove client sockets with issues
                for (Integer idx : removeClientsIndex) {
                    ClientSocket clientSocket = clientSockets.get(idx);
                    clientSockets.remove(idx.intValue());
                    closeClientSocket(clientSocket);
                }

                Thread.sleep(threadSleep);
                System.out.println("IOE: finished.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void closeClientSocket(ClientSocket clientSocket) {
        if (clientSocket != null) {
            clientSocket.close();
            ClientPacket clientPacket = new ClientPacket(-1, 0, MetaType.USER, clientSocket.getClientId(), ClientOp.CLIENT_DELETE);
            PacketQueue.writeIn(clientPacket);
        }
    }

    public void addUserSocket(ClientSocket clientSocket) {
        if (!clientSockets.contains(clientSocket)) {
            clientSockets.add(clientSocket);
            ClientPacket clientPacket = new ClientPacket(-1, 0, MetaType.USER, clientSocket.getClientId(), ClientOp.CLIENT_CREATE);
            PacketQueue.writeIn(clientPacket);
        }
    }


}
