package org.teilen.server.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import org.teilen.common.packet.base.Packet;

public class PacketQueue {

  private static final Queue<Packet> in = new LinkedList<>();
  private static final Map<Integer, Queue<Packet>> out = new ConcurrentHashMap<>();

  public PacketQueue() {
  }

  //==================================================
  //OUT QUEUE

  public static List<Packet> readOut(Integer userId, int packetNumber) {
    Queue<Packet> queue = out.get(userId);
    if (queue == null) {
      return null;
    }

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
    List<Packet> allClientsPackets = packetMap.get(0);
    if (allClientsPackets != null && allClientsPackets.size() != 0) {
      for (Map.Entry<Integer, List<Packet>> packetEntry : packetMap.entrySet()) {
        Integer clientId = packetEntry.getKey();
        List<Packet> clientPackets = packetEntry.getValue();
        Queue<Packet> existingPackets = out.get(clientId);
        if (existingPackets != null) {
          existingPackets.addAll(clientPackets);
          existingPackets.addAll(allClientsPackets);
        } else {
          Queue<Packet> queue = new LinkedList<>(clientPackets);
          queue.addAll(allClientsPackets);
          out.put(clientId, queue);
        }
      }
    } else {
      for (Map.Entry<Integer, List<Packet>> packetEntry : packetMap.entrySet()) {
        Integer clientId = packetEntry.getKey();
        List<Packet> clientPackets = packetEntry.getValue();
        Queue<Packet> existingPackets = out.get(clientId);
        if (existingPackets != null) {
          existingPackets.addAll(clientPackets);
        } else {
          out.put(clientId, new LinkedList<>(clientPackets));
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
      if (queueSize == 0) {
        return null;
      }

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
