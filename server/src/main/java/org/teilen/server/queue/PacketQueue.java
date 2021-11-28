package org.teilen.server.queue;

import org.teilen.common.packet.Packet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public static void writePackets(Packet... packets) {
        if (packets != null) {
            synchronized (out) {
                for (int i = 0; i < packets.length; i++) {
                    out.add(packets[i]);
                }
            }
        }
    }

    public static List<Packet> readPackets(int packetNumber) {
        synchronized (in) {
            int queueSize = in.size();
            if (queueSize == 0)
                return null;

            int diff = queueSize - packetNumber;
            if (diff == 0) {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < queueSize; i++) {
                    list.add(in.poll());
                }
                return list;
            } else if (diff > 0) {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < packetNumber; i++) {
                    list.add(in.poll());
                }
                return list;
            } else {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < queueSize; i++) {
                    list.add(in.poll());
                }
                return list;
            }
        }
    }
}
