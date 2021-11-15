package org.teilen.client.queue;

import org.teilen.common.packet.Packet;

import java.util.LinkedList;
import java.util.Queue;

public class PacketQueue {
    private final Queue<Packet> in;
    private final Queue<Packet> out;

    public PacketQueue() {
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
    }


    public Packet readPacket() {
        synchronized (out) {
            return this.out.poll();
        }
    }

    public boolean writePacket(Packet Packet) {
        synchronized (in) {
            return this.in.add(Packet);
        }
    }


}
