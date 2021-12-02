package org.teilen.client.engine;

import org.teilen.client.domain.SocketMeta;
import org.teilen.client.queue.PacketQueue;
import org.teilen.client.util.LogUtil;
import org.teilen.common.packet.Packet;
import org.teilen.common.packet.comm.Request;
import org.teilen.common.packet.comm.Response;
import org.teilen.common.packet.comm.Status;
import org.teilen.common.packet.meta.ConnOp;
import org.teilen.common.packet.meta.ConnPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class IOEngine implements Runnable {
    private Boolean connected = false;
    private Socket socket;
    private SocketMeta socketMeta;
    private static final int threadSleep = 2000; //millis
    private static final int packetNumber = 5;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public IOEngine() {
    }

    public void run() {
        try {
            LogUtil.info("Trying to connect to server : " + socketMeta);
            while (true) {
                while (connected) {

                    try {
                        if (out == null)
                            this.out = new ObjectOutputStream(this.socket.getOutputStream());

                        if (in == null)
                            this.in = new ObjectInputStream(this.socket.getInputStream());

                        //Receiving from server to client
                        Object inObject = in.readObject();
                        while (inObject == null) {
                            inObject = in.readObject();
                        }
                        if (inObject instanceof Request) {
                            Request request = (Request) inObject;
                            List<Packet> packets = new ArrayList<>();
                            Response response = new Response(1, Status.OK);
                            out.writeObject(response);
                            out.flush();
                            System.out.println("Client : acceptance response for server sent " + response);
                            for (int i = 0; i < request.getPackets(); i++) {
                                Object inPacket = in.readObject();
                                while (inPacket == null) {
                                    inPacket = in.readObject();
                                }
                                Packet packet = (Packet) inPacket;
                                out.writeObject(response);
                                out.flush();
                                packets.add(packet);
                                System.out.println("Client : received from server " + packet + ", client-response " + response);
                            }
                            PacketQueue.ioWriteIn(packets);
                        }


                        //Sending from client to server
                        List<Packet> clientPackets = PacketQueue.ioReadOut(packetNumber);
                        System.out.println("Client : packets " + clientPackets);
                        int clientPacketsNr = clientPackets != null ? clientPackets.size() : 0;
                        out.writeObject(new Request(0, clientPacketsNr));
                        out.flush();

                        inObject = in.readObject();
                        while (inObject == null) {
                            inObject = in.readObject();
                        }

                        if (inObject instanceof Response) {
                            Response response = (Response) inObject;
                            if (response.getStatus().name().equals(Status.OK.name())) {
                                for (int i = 0; i < clientPacketsNr; i++) {
                                    Packet packet = clientPackets.get(i);
                                    out.writeObject(packet);
                                    out.flush();
                                    Object packetObject = in.readObject();
                                    while (packetObject == null) {
                                        packetObject = in.readObject();
                                    }
                                    Response packetResponse = null;
                                    if (packetObject instanceof Response) {
                                        packetResponse = (Response) packetObject;
                                        System.out.println("Client : - " + i + " sent packet " + packet + ", server-response " + packetResponse);
                                    }
                                    if (!packetResponse.getStatus().name().equals(Status.OK.name())) {
                                        System.out.println("Client : stopped sending packets , server-response " + packetResponse);
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        disconnect();
                    }
                    Thread.sleep(threadSleep);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Public methods to be called from info panel buttons
    public synchronized void connect(SocketMeta socketMeta) throws IOException {
        if (this.socketMeta == null) {
            this.socketMeta = socketMeta;
        } else {
            synchronized (this.socketMeta) {
                this.socketMeta = socketMeta;
            }
        }

        synchronized (this.connected) {
            if (this.connected == false) {
                this.socket = new Socket(socketMeta.getSocketHost(), socketMeta.getSocketPort());
                this.connected = true;
                LogUtil.info("Socket connected : " + this.socket);
                PacketQueue.ioWriteIn(new ConnPacket(ConnOp.ON));
            }
        }
    }

    public synchronized void disconnect() throws IOException {
        synchronized (this.connected) {
            if (this.connected) {
                this.connected = false;
                try {
                    this.out.close();
                    this.out = null;
                } catch (Exception e) {
                }
                try {
                    this.in.close();
                    this.in = null;
                } catch (Exception e) {
                }

                this.socket.close();
                LogUtil.info("Socket disconnected : " + this.socket);
                this.socket = null;

                PacketQueue.ioWriteIn(new ConnPacket(ConnOp.OFF));
            }
        }
    }

}
