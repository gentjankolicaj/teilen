package org.teilen.client.queue;

import org.teilen.common.packet.base.Packet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacketQueue {
    private static final Queue<Packet> in = new LinkedList<>();
    private static final Queue<Packet> out = new LinkedList<>();

    public PacketQueue() {
    }


    public static Packet ioReadOut() {
        synchronized (out) {
            return out.poll();
        }
    }

    public static List<Packet> ioReadOut(int packetNumber) {
        synchronized (out) {
            int queueSize = out.size();
            if (queueSize == 0)
                return null;

            int diff = queueSize - packetNumber;
            if (diff == 0) {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < queueSize; i++) {
                    list.add(out.poll());
                }
                return list;
            } else if (diff > 0) {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < packetNumber; i++) {
                    list.add(out.poll());
                }
                return list;
            } else {
                List<Packet> list = new ArrayList<>();
                for (int i = 0; i < queueSize; i++) {
                    list.add(out.poll());
                }
                return list;
            }
        }
    }


    public static boolean ioWriteIn(Packet packet) {
        synchronized (in) {
            return in.add(packet);
        }
    }

    public static void ioWriteIn(Packet... packets) {
        if (packets != null && packets.length != 0) {
            synchronized (in) {
                for (int i = 0; i < packets.length; i++) {
                    in.add(packets[i]);
                }
            }
        }
    }

    public static void ioWriteIn(List<Packet> packets) {
        if (packets != null && packets.size() != 0) {
            synchronized (in) {
                for (int i = 0; i < packets.size(); i++) {
                    in.add(packets.get(i));
                }
            }
        }
    }


    public static void activityWriteOut(Packet... packets) {
        if (packets != null) {
            synchronized (out) {
                for (int i = 0; i < packets.length; i++) {
                    out.add(packets[i]);
                }
            }
        }
    }

    public static void activityWriteOut(List<Packet> packets) {
        if (packets != null && packets.size() != 0) {
            synchronized (out) {
                for (int i = 0; i < packets.size(); i++) {
                    out.add(packets.get(i));
                }
            }
        }
    }

    public static List<Packet> activityReadIn(int packetNumber) {
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
