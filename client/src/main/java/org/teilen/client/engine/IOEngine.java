package org.teilen.client.engine;

import org.teilen.client.domain.SocketMeta;
import org.teilen.client.domain.SocketWrapper;
import org.teilen.client.queue.PacketQueue;
import org.teilen.client.util.LogUtil;
import org.teilen.common.packet.base.Packet;
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
    private static final int threadSleep = 2000; //millis
    private static final int packetNumber = 5;
    private static final long readWriteTimeout = 100;

    private Boolean connected = false;
    private SocketWrapper socketWrapper;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public IOEngine() {
    }

    public void run() {
        try {
            while (true) {
                if (connected) {
                    out = socketWrapper.getOut();
                    in = socketWrapper.getIn();
                }
                while (connected) {
                    try {

                        //Receiving from server to client
                        Object inObject = in.readObject();
                        long start = System.currentTimeMillis();
                        long diff = start;
                        while (inObject == null) {
                            inObject = in.readObject();
                            diff = System.currentTimeMillis();
                            if (diff - start <= readWriteTimeout) {
                                LogUtil.info("Client : - failed to receive Proposal-Request from server.Timeout " + (diff - start));
                                break;
                            }
                        }
                        if (inObject instanceof Request) {
                            Request firstRequest = (Request) inObject;
                            List<Packet> packets = new ArrayList<>();
                            Response firstResponse = new Response(1, Status.OK);
                            out.writeObject(firstResponse);
                            out.flush();
                            LogUtil.info("Client : From server received " + firstRequest);
                            LogUtil.info("Client : To server sent " + firstResponse);
                            int packetNr = firstRequest.getPackets();
                            Outer:
                            for (int i = 0; i < packetNr; i++) {
                                Object inPacket = in.readObject();
                                start = System.currentTimeMillis();
                                diff = start;
                                while (inPacket == null) {
                                    inPacket = in.readObject();
                                    diff = System.currentTimeMillis();
                                    if (diff - start <= readWriteTimeout) {
                                        LogUtil.info("Client : - " + i + " failed to receive packet from server.Receiving stopped.Timeout : " + (diff - start));
                                        break Outer;
                                    }
                                }
                                Packet packet = (Packet) inPacket;
                                out.writeObject(firstResponse);
                                out.flush();
                                packets.add(packet);
                                System.out.println("Client : received from server " + packet + ", client-response " + firstResponse);
                            }
                            PacketQueue.writeIn(packets);
                        }


                        //Sending from client to server
                        List<Packet> clientPackets = PacketQueue.readOut(packetNumber);
                        System.out.println("Client : packets " + clientPackets);
                        int clientPacketsNr = clientPackets != null ? clientPackets.size() : 0;
                        Request secondRequest = new Request(0, clientPacketsNr);
                        out.writeObject(secondRequest);
                        out.flush();

                        inObject = in.readObject();
                        start = System.currentTimeMillis();
                        diff = start;
                        while (inObject == null) {
                            inObject = in.readObject();
                            diff = System.currentTimeMillis();
                            if (diff - start <= readWriteTimeout) {
                                LogUtil.info("Client : - failed to receive Request-Response from server.Timeout " + (diff - start));
                                break;
                            }
                        }

                        if (inObject instanceof Response) {
                            Response secondResponse = (Response) inObject;
                            LogUtil.info("Client : To server sent " + secondRequest);
                            LogUtil.info("Client : From server received " + secondResponse);
                            if (secondResponse.getStatus().name().equals(Status.OK.name())) {
                                Outer:
                                for (int i = 0; i < clientPacketsNr; i++) {
                                    Packet packet = clientPackets.get(i);
                                    out.writeObject(packet);
                                    out.flush();
                                    Object outPacket = in.readObject();
                                    start = System.currentTimeMillis();
                                    diff = start;
                                    while (outPacket == null) {
                                        outPacket = in.readObject();
                                        diff = System.currentTimeMillis();
                                        if (diff - start <= readWriteTimeout) {
                                            LogUtil.info("Client : - " + i + " failed to sent packet to server " + packet + ", server-response " + outPacket + ".Sending stopped.Timeout : " + (diff - start));
                                            break Outer;
                                        }
                                    }

                                    Response packetResponse = null;
                                    if (outPacket instanceof Response) {
                                        packetResponse = (Response) outPacket;
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
                Thread.sleep(threadSleep);
                LogUtil.info("Waiting to connect to server...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Public methods to be called from info panel buttons
    public synchronized void connect(SocketMeta socketMeta) throws IOException {
            if (this.connected == false) {
                Socket socket = new Socket(socketMeta.getSocketHost(), socketMeta.getSocketPort());
                this.socketWrapper = new SocketWrapper(socket);
                this.connected = true;
                LogUtil.info("Socket connected : " + this.socketWrapper);
                PacketQueue.writeIn(new ConnPacket(ConnOp.ON));
            }

    }

    public synchronized void disconnect() throws IOException {
            if (this.connected) {
                this.socketWrapper.close();
                this.connected = false;
                LogUtil.info("Socket disconnected : " + this.socketWrapper);
                PacketQueue.writeIn(new ConnPacket(ConnOp.OFF));
            }
    }

}
