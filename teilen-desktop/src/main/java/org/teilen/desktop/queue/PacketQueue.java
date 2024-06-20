package org.teilen.desktop.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.teilen.common.packet.base.Packet;

public class PacketQueue {

  private static final Queue<Packet> in = new LinkedList<>();
  private static final Queue<Packet> out = new LinkedList<>();

  public PacketQueue() {
  }

  //=================================================
  //R-W out queue

  public static Packet readOut() {
    synchronized (out) {
      return out.poll();
    }
  }

  public static List<Packet> readOut(int packetNumber) {
    synchronized (out) {
      int queueSize = out.size();
      if (queueSize == 0) {
        return null;
      }

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

  public static void writeOut(Packet... packets) {
    if (packets != null) {
      synchronized (out) {
        Collections.addAll(out, packets);
      }
    }
  }

  public static void writeOut(List<Packet> packets) {
    if (packets != null && packets.size() != 0) {
      synchronized (out) {
        for (int i = 0; i < packets.size(); i++) {
          out.add(packets.get(i));
        }
      }
    }
  }

  //=================================================
  //R-W in queue

  public static boolean writeIn(Packet packet) {
    synchronized (in) {
      return in.add(packet);
    }
  }

  public static void writeIn(Packet... packets) {
    if (packets != null && packets.length != 0) {
      synchronized (in) {
        Collections.addAll(in, packets);
      }
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
