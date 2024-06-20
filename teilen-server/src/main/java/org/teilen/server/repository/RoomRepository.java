package org.teilen.server.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;

public class RoomRepository {

  static final Map<Integer, Room> rooms = new ConcurrentHashMap<>();


  public static Room findRoomById(Integer roomId) {
    return rooms.get(roomId);
  }

  public static List<Room> findAllList() {
    return rooms.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
  }

  public static Map<Integer, Room> findAll() {
    return rooms;
  }

  public static Room findRoomByClientIds(Integer firstClientId, Integer secondClientId) {
    Set<Integer> clientIds = new TreeSet<>();
    clientIds.add(firstClientId);
    clientIds.add(secondClientId);
    Room room = null;
    for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
      Room tmpRoom = entry.getValue();
      if (tmpRoom != null) {
        Set<Integer> existingClientIds = tmpRoom.getClients();
        if (existingClientIds != null && existingClientIds.size() != 0) {
          boolean containsIds = clientIds.containsAll(existingClientIds);
          if (containsIds) {
            room = tmpRoom;
            break;
          }
        }
      }
    }
    return room;
  }

  public static Room findRoomByClientIds(Set<Integer> clientIds) {
    if (clientIds == null || clientIds.size() == 0) {
      return null;
    } else {
      Room room = null;
      for (Map.Entry<Integer, Room> roomEntry : rooms.entrySet()) {
        Room tmpRoom = roomEntry.getValue();
        if (tmpRoom != null) {
          Set<Integer> existingClientIds = tmpRoom.getClients();
          if (existingClientIds != null && existingClientIds.size() != 0) {
            boolean containsIds = clientIds.containsAll(existingClientIds);
            if (containsIds) {
              room = tmpRoom;
              break;
            }
          }
        }
      }
      return room;
    }
  }

  public static void insertRoom(Room room) {
    rooms.put(room.getId(), room);
  }

  public static void updateRoom(Room room) {
    rooms.replace(room.getId(), room);
  }


  public static Room createRoomByServer(Set<Integer> clientIds) {
    Room room = new Room(-1, "Normal-chat");
    room.setClients(clientIds);
    rooms.put(room.getId(), room);
    return room;
  }

  public static Room createRoomByClient(Integer ownerId, Set<Integer> clientIds) {
    Room room = new Room(ownerId, "Multiple-chat");
    room.setClients(clientIds);
    rooms.put(room.getId(), room);
    return room;
  }


  public static void deleteRoom(Integer roomId) {
    rooms.remove(roomId);
  }

  //===================================================================
  //Client crud
  public static boolean insertClient(Integer roomId, Client client) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.addClient(client);
      return true;
    } else {
      return false;
    }
  }

  public static boolean deleteClient(Integer roomId, Client client) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.removeClient(client);
      return true;
    } else {
      return false;
    }
  }


  //=============================================================
  //Packet crud
  public static boolean insertRoomContentByServer(Integer roomId, RoomContent roomContent) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.addRoomContentByGlobal(roomContent);
      return true;
    } else {
      return false;
    }
  }

  public static boolean insertRoomContentByLocal(Integer roomId, RoomContent roomContent) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.addRoomContentByLocal(roomContent);
      return true;
    } else {
      return false;
    }
  }

  public static boolean deleteRoomContentByServer(Integer roomId, Integer globalId) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.removeRoomContentByGlobal(globalId);
      return true;
    } else {
      return false;
    }
  }

  public static boolean deleteRoomContentByLocal(Integer roomId, Integer localId) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.removeRoomContentByLocal(localId);
      return true;
    } else {
      return false;
    }
  }


  public static boolean updateRoomContentByServer(Integer roomId, RoomContent roomContent) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.updateRoomContentByGlobal(roomContent);
      return true;
    } else {
      return false;
    }
  }

  public static boolean updateRoomContentByLocal(Integer roomId, RoomContent roomContent) {
    Room room = findRoomById(roomId);
    if (room != null) {
      room.updateRoomContentByLocal(roomContent);
      return true;
    } else {
      return false;
    }
  }


}
