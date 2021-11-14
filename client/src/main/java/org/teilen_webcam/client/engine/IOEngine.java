package org.teilen_webcam.client.engine;

import org.teilen_webcam.client.domain.meta.SocketMeta;
import org.teilen_webcam.client.queue.PacketQueue;
import org.teilen_webcam.client.util.LogUtil;
import org.teilen_webcam.common.packet.AbstractPacket;
import org.teilen_webcam.common.packet.meta.ConnectionOperation;
import org.teilen_webcam.common.packet.meta.ConnectionPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOEngine implements Runnable {
    private final PacketQueue packetQueue;
    private Boolean connected = false;
    private Socket socket;
    private SocketMeta socketMeta;

    public IOEngine(PacketQueue packetQueue) {
        this.packetQueue = packetQueue;
    }

    public void run() {
        try {
            LogUtil.info("Trying to connect to server : " + socketMeta);
            while (true) {
                while (connected) {
                    try {
                        //Read from queue &
                        //Write to socket
                        boolean write = false;
                        try {
                            AbstractPacket out = packetQueue.readPacket();
                            ObjectOutputStream objectInputStream = new ObjectOutputStream(socket.getOutputStream());
                            objectInputStream.writeObject(out);
                            objectInputStream.flush();
                            write = true;
                            LogUtil.info("From queue-socket : " + out);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Read from socket
                        //& Write to queue
                        boolean read = false;
                        if (write) {
                            try {
                                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                                Object packet = objectInputStream.readObject();
                                if (packet != null) {
                                    AbstractPacket in = (AbstractPacket) packet;
                                    packetQueue.writePacket(in);
                                    read = true;
                                    LogUtil.info("From socket-queue : " + in);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        if (!write && !read) {
                            packetQueue.writePacket(new ConnectionPacket(ConnectionOperation.OFF));
                            disconnect();
                        }

                    } catch (Exception e) {

                    }
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {

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
                this.packetQueue.writePacket(new ConnectionPacket(ConnectionOperation.ON));
            }
        }
    }

    public synchronized void disconnect() throws IOException {
        synchronized (this.connected) {
            if (this.connected) {
                this.connected = false;
                this.socket.close();
                LogUtil.info("Socket disconnected : " + this.socket);
                this.socket = null;
                this.packetQueue.writePacket(new ConnectionPacket(ConnectionOperation.ON));
            }
        }
    }

}
