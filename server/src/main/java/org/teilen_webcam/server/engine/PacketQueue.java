package org.teilen_webcam.server.engine;

import org.teilen_webcam.common.packet.AbstractPacket;

import java.util.LinkedList;
import java.util.Queue;

public class PacketQueue {

    private static final Queue<AbstractPacket> in = new LinkedList<>();
    private static final Queue<AbstractPacket> out = new LinkedList<>();

    public PacketQueue() {
    }


    public static AbstractPacket readPacket() {
        synchronized (out) {
            return out.poll();
        }
    }

    public static boolean writePacket(AbstractPacket abstractPacket) {
        synchronized (in) {
            return in.add(abstractPacket);
        }
    }

}
