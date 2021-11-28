package org.teilen.server.engine;

import org.teilen.common.packet.Packet;
import org.teilen.common.packet.meta.UserOp;
import org.teilen.common.packet.meta.UserPacket;
import org.teilen.server.domain.UserSocket;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.util.LogUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOEngine implements Runnable {
    public List<UserSocket> userSockets;

    public IOEngine() {
        this.userSockets = Collections.synchronizedList(new ArrayList<>());
    }

    public void run() {
        while (true) {
            try {
                for (int i = 0; i < userSockets.size(); i++) {
                    UserSocket userSocket = userSockets.get(i);
                    Integer id = userSocket.getId();
                    Socket socket = userSocket.getSocket();

                    //Read from queue &
                    //Write to socket
                    boolean write = false;
                    try {
                        Packet out = PacketQueue.readPacket();
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
                                Packet in = (Packet) packet;
                                PacketQueue.writePacket(in);
                                read = true;
                                LogUtil.info("From socket-queue : " + in);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (!write && !read) {
                        removeUserSocket(userSocket);
                    }
                }
                //Todo : solve dead-lock on socketList
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }

    }

    private void removeUserSocket(UserSocket userSocket) {
        synchronized (this.userSockets) {
            try {
                userSocket.getSocket().close();
            } catch (Exception e) {
            }
            this.userSockets.remove(userSocket);
            PacketQueue.writePacket(new UserPacket(UserOp.USER_DELETE, userSocket.getId()));
        }
    }

    public void addUserSocket(UserSocket userSocket) {
        synchronized (this.userSockets) {
            this.userSockets.add(userSocket);
            PacketQueue.writePacket(new UserPacket(UserOp.USER_CREATE, userSocket.getId()));
        }
    }


}
