package org.teilen_webcam.client.engine;

import org.teilen_webcam.common.packet.AbstractPacket;
import org.teilen_webcam.common.packet.media.MediaPacket;
import org.teilen_webcam.common.packet.meta.MetadataPacket;

import java.util.LinkedList;
import java.util.Queue;

public class QueueEngine implements Runnable {
    private final MediaEngine mediaEngine;
    private final Queue<AbstractPacket> in;
    private final Queue<AbstractPacket> out;

    public QueueEngine(MediaEngine mediaEngine) {
        this.mediaEngine = mediaEngine;
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            sendToEngine();
            receiveFromEngine();
        }

    }

    public AbstractPacket readPacket() {
        synchronized (out) {
            return this.out.poll();
        }
    }

    public boolean writePacket(AbstractPacket abstractPacket) {
        synchronized (in) {
            return this.in.add(abstractPacket);
        }
    }

    private void sendToEngine() {
        synchronized (in) {
            AbstractPacket inPacket = in.poll();
            if (inPacket != null) {
                if (inPacket instanceof MetadataPacket) {

                } else if (inPacket instanceof MediaPacket) {

                }
            }
        }
    }

    private void receiveFromEngine() {
        synchronized (out) {

        }
    }

}
