package org.teilen.server.queue;

import org.teilen.common.packet.Packet;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PacketQueue {
    private static final Queue<Packet> in = new LinkedList<>();
    private static final Map<Integer, Queue<Packet>> out = new ConcurrentHashMap<>();

    public PacketQueue() {
    }


    //==================================================
    //OUT QUEUE

    public static List<Packet> readOut(Integer userId, int packetNumber) {
        Queue<Packet> queue = out.get(userId);
        if (queue == null)
            return null;

        int queueSize = queue.size();
        int diff = queueSize - packetNumber;
        if (diff == 0) {
            List<Packet> list = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                list.add(queue.poll());
            }
            return list;
        } else if (diff > 0) {
            List<Packet> list = new ArrayList<>();
            for (int i = 0; i < packetNumber; i++) {
                list.add(queue.poll());
            }
            return list;
        } else {
            List<Packet> list = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                list.add(queue.poll());
            }
            return list;
        }
    }


    public static void writeOut(Map<Integer, List<Packet>> packetMap) {
        for (Map.Entry<Integer, List<Packet>> packetEntry : packetMap.entrySet()) {
            Integer clientId = packetEntry.getKey();
            List<Packet> packets = packetEntry.getValue();
            Queue<Packet> existingPackets = out.get(clientId);
            if (existingPackets != null) {
                existingPackets.addAll(packets);
            } else {
                out.put(clientId, new LinkedList<>(packets));
            }
        }
    }

    public static void writeOutToAllExceptOrigin(Map<Integer, Packet> allClientsPacket) {
        for (Map.Entry<Integer, Packet> packetEntry : allClientsPacket.entrySet()) {
            Integer clientId = packetEntry.getKey();
            Packet packet = packetEntry.getValue();
            for (Map.Entry<Integer, Queue<Packet>> outEntry : out.entrySet()) {
                Integer outClientId = outEntry.getKey();
                if (clientId.intValue() != outClientId.intValue()) {
                    outEntry.getValue().add(packet);
                }
            }
        }
    }


    //==================================================
    //IN QUEUE
    public static boolean writeIn(Packet packet) {
        synchronized (in) {
            return in.add(packet);
        }
    }


    public static void writeIn(List<Packet> packets) {
        if (packets != null && packets.size() != 0) {
            synchronized (in) {
                for (int i = 0; i < packets.size(); i++) {
                    in.add(packets.get(i));
                }
            }
        }
    }


    public static List<Packet> readIn(int packetNumber) {
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
