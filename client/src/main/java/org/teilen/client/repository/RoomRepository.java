package org.teilen.client.repository;

import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoomRepository {
    static Integer roomIdCounter = 0;
    static final Map<Integer, Room> rooms = new HashMap<>();

    private static Integer getRoomId() {
        roomIdCounter++;
        return roomIdCounter;
    }


    public static Room findRoomById(Integer roomId) {
        return rooms.get(roomId);
    }

    public static Room findRoomByUserIds(Integer firstUserId, Integer secondUserId) {
        Room room = null;
        search:
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room actualRoom = entry.getValue();
            if (actualRoom != null && actualRoom.getOwnerId() == null) {
                Set<Integer> clientIds = actualRoom.getClients();
                if (clientIds != null) {
                    int matchesFound = 0;
                    for (Integer clientId : clientIds) {
                        if (clientId.intValue() == firstUserId.intValue()) {
                            matchesFound++;
                            continue;
                        } else if (clientId.intValue() == secondUserId.intValue()) {
                            matchesFound++;
                            continue;
                        } else if (matchesFound == 2) {
                            room = actualRoom;
                            break search;
                        }
                    }
                }
            }
        }
        return room;
    }

    public static void insertRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public static void updateRoom(Room room) {
        rooms.replace(room.getId(), room);
    }

    public static Room createRoomByServer(Client firstClient, Client secondClient) {
        Room room = findRoomByUserIds(firstClient.getId(), secondClient.getId());
        if (room == null) {
            room = new Room(getRoomId(), firstClient, secondClient);
        }
        return room;
    }

    public static Room createRoomByServer(Client owner) {
        Room room = new Room(getRoomId(), owner.getId());
        room.addClient(owner);
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
        } else
            return false;
    }

    public static boolean deleteClient(Integer roomId, Client client) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeClient(client);
            return true;
        } else
            return false;
    }


    //=============================================================
    //Packet crud
    public static boolean insertRoomContentByServer(Integer roomId, RoomContent roomContent) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addRoomContentByGlobal(roomContent);
            return true;
        } else
            return false;
    }

    public static boolean insertRoomContentByLocal(Integer roomId, RoomContent roomContent) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addRoomContentByLocal(roomContent);
            return true;
        } else
            return false;
    }

    public static boolean deleteRoomContentByServer(Integer roomId, Integer globalId) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeRoomContentByGlobal(globalId);
            return true;
        } else
            return false;
    }

    public static boolean deleteRoomContentByLocal(Integer roomId, Integer localId) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeRoomContentByLocal(localId);
            return true;
        } else
            return false;
    }


    public static boolean updateRoomContentByServer(Integer roomId, RoomContent roomContent) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.updateRoomContentByGlobal(roomContent);
            return true;
        } else
            return false;
    }

    public static boolean updateRoomContentByLocal(Integer roomId, RoomContent roomContent) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.updateRoomContentByLocal(roomContent);
            return true;
        } else
            return false;
    }


}
