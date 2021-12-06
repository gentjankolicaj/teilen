package org.teilen.server.repository;

import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.packet.base.Packet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository {

    static final Map<Integer, Room> rooms = new HashMap<>();
    static Integer roomCounter = 0;

    static Room findRoomById(Integer roomId) {
        return rooms.get(roomId);
    }

    static Room findRoomByUserIds(Integer firstUserId, Integer secondUserId) {
        Room room = null;
        search:
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room actualRoom = entry.getValue();
            if (actualRoom != null && actualRoom.getOwnerId() == null) {
                List<Integer> clientIds = actualRoom.getClients();
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

    static Room createRoom(Client firstClient, Client secondClient) {
        Room room = findRoomByUserIds(firstClient.getId(), secondClient.getId());
        if (room == null) {
            room = new Room(roomCounter, firstClient, secondClient);
        }
        return room;
    }

    static Room createRoomWithOwner(Client owner) {
        Room room = new Room(roomCounter, owner.getId());
        room.addClient(owner);
        return room;
    }


    static boolean insertClient(Integer roomId, Client client) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addClient(client);
            return true;
        } else
            return false;
    }

    static boolean deleteClient(Integer roomId, Client client) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeClient(client);
            return true;
        } else
            return false;
    }


    static boolean insertPacket(Integer roomId, Packet packet) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addRoomContent(packet);
            return true;
        } else
            return false;
    }

    static boolean deletePacket(Integer roomId, Integer contentId) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeRoomContent(contentId);
            return true;
        } else
            return false;
    }

    static boolean updatePacket(Integer roomId, Integer contentId, Packet packet) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.updateRoomContent(contentId, packet);
            return true;
        } else
            return false;
    }


}
