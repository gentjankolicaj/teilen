package org.teilen_webcam.server.engine;

import org.teilen_webcam.common.domain.UserSocket;
import org.teilen_webcam.common.packet.AbstractPacket;
import org.teilen_webcam.common.packet.meta.UserOperation;
import org.teilen_webcam.common.packet.meta.UserPacket;
import org.teilen_webcam.server.util.LogUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOEngine implements Runnable {
    public List<UserSocket> userSockets;
    public QueueEngine queueEngine;

    public IOEngine(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
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
                        AbstractPacket out = queueEngine.readPacket();
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
                                queueEngine.writePacket(in);
                                read = true;
                                LogUtil.info("From socket-queue : " + in);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (!write && !read) {
                        removeUserSocket(userSocket);
                        queueEngine.writePacket(new UserPacket(UserOperation.USER_DELETE));
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
        }
    }

    public void addUserSocket(UserSocket userSocket) {
        synchronized (this.userSockets) {
            this.userSockets.add(userSocket);
        }
    }


}
