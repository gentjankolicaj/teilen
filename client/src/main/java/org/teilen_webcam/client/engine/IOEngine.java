package org.teilen_webcam.client.engine;

import org.teilen_webcam.client.meta.SocketMeta;
import org.teilen_webcam.client.util.LogUtil;
import org.teilen_webcam.common.packet.AbstractPacket;
import org.teilen_webcam.common.packet.meta.ClientConnStatePacket;
import org.teilen_webcam.common.packet.meta.MetaType;

import java.io.IOException;
import java.net.Socket;

public class IOEngine implements Runnable {
    private final QueueEngine queueEngine;
    private Boolean connected = false;
    private Socket socket;
    private SocketMeta socketMeta;

    public IOEngine(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
    }

    public void run() {
        try {
            LogUtil.info("Trying to connect to server : " + socketMeta);
            while (true) {
                while (connected) {
                    /**
                     //Read from queue &
                     //Write to socket
                     AbstractPacket out = queueEngine.readPacket();
                     writeToSocket(out);

                     //Read from socket
                     //& Write to queue
                     AbstractPacket in = readFromSocket();
                     queueEngine.writePacket(in);
                     */
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {

        }
    }

    private void writeToSocket(AbstractPacket abstractPacket) {

    }

    private AbstractPacket readFromSocket() {
        return null;
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
                this.connected = true;
                this.socket = new Socket(socketMeta.getSocketHost(), socketMeta.getSocketPort());
                LogUtil.info("Socket connected : " + this.socket);
                queueEngine.writePacket(new ClientConnStatePacket(MetaType.CLIENT_CONNECTED));
            }
        }
    }

    public synchronized void disconnect() throws IOException {
        synchronized (this.connected) {
            if (this.connected) {
                this.connected = false;
                this.socket.close();
                this.socket = null;
                queueEngine.writePacket(new ClientConnStatePacket(MetaType.CLIENT_DISCONNECTED));

            }
        }
    }

}
