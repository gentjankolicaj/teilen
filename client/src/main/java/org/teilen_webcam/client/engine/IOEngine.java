package org.teilen_webcam.client.engine;

import org.teilen_webcam.client.meta.SocketMeta;
import org.teilen_webcam.client.util.LogUtil;
import org.teilen_webcam.common.packet.AbstractPacket;

import java.net.Socket;

public class IOEngine implements Runnable {
    private final QueueEngine queueEngine;
    Boolean connected = false;
    private Socket socket;
    private SocketMeta socketMeta;

    public IOEngine(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
    }

    public void run() {
        while (true) {
            while (connected) {
                //Read from queue &
                //Write to socket
                AbstractPacket out = queueEngine.readPacket();
                writeToSocket(out);

                //Read from socket
                //& Write to queue
                AbstractPacket in = readFromSocket();
                queueEngine.writePacket(in);
            }
            tryToConnect();
        }
    }

    private void writeToSocket(AbstractPacket abstractPacket) {

    }

    private AbstractPacket readFromSocket() {
        return null;
    }

    private void tryToConnect() {
        try {
            LogUtil.info("Trying to connect");
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }


    //Public methods to be called from info panel buttons
    public void connect(SocketMeta socketMeta) {
        if (this.socketMeta != null) {
            synchronized (this.socketMeta) {
                this.socketMeta = socketMeta;
            }
        } else {
            this.socketMeta = socketMeta;
        }

        synchronized (this.connected) {
            this.connected = true;
        }
    }

    public void disconnect() {
        synchronized (this.connected) {
            this.connected = false;
        }
        try {
            this.socket.close();
        } catch (Exception e) {

        }
    }

}
