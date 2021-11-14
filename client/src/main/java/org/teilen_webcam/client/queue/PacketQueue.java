package org.teilen_webcam.client.queue;

import org.teilen_webcam.common.packet.AbstractPacket;

import java.util.LinkedList;
import java.util.Queue;

public class PacketQueue {
    private final Queue<AbstractPacket> in;
    private final Queue<AbstractPacket> out;

    public PacketQueue() {
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
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


}
