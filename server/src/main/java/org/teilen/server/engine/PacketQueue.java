package org.teilen.server.engine;

import org.teilen.common.packet.Packet;

import java.util.LinkedList;
import java.util.Queue;

public class PacketQueue {

    private static final Queue<Packet> in = new LinkedList<>();
    private static final Queue<Packet> out = new LinkedList<>();

    public PacketQueue() {
    }


    public static Packet readPacket() {
        synchronized (out) {
            return out.poll();
        }
    }

    public static boolean writePacket(Packet packet) {
        synchronized (in) {
            return in.add(packet);
        }
    }

}
