package org.teilen.client.repository;

import org.teilen.common.domain.Room;
import org.teilen.common.domain.User;
import org.teilen.common.packet.Packet;

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
            Room tmpRoom = entry.getValue();
            if (tmpRoom != null) {
                List<User> users = tmpRoom.getUsers();
                if (users != null) {
                    boolean flag = false;
                    for (User user : users) {
                        Integer id = user.getId();
                        if (!flag && id != null) {
                            flag = firstUserId.intValue() == id.intValue();
                        } else if (flag && id != null) {
                            boolean found = secondUserId.intValue() == id.intValue();
                            if (found) {
                                room = tmpRoom;
                                break search;
                            }
                        }
                    }
                }
            }
        }
        return room;
    }

    static Room createRoom(User firstUser, User secondUser) {
        Room room = findRoomByUserIds(firstUser.getId(), secondUser.getId());
        if (room == null) {
            room = new Room(roomCounter, firstUser, secondUser);
        }
        return room;
    }

    static Room createRoomWithOwner(User owner) {
        Room room = new Room(roomCounter, owner.getId());
        room.addUser(owner);
        return room;
    }


    static boolean insertUser(Integer roomId, User newUser) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addUser(newUser);
            return true;
        } else
            return false;
    }

    static boolean deleteUser(Integer roomId, User newUser) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removeUser(newUser);
            return true;
        } else
            return false;
    }

    static boolean updateUser(Integer roomId, User newUser) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addUser(newUser);
            return true;
        } else
            return false;
    }


    static boolean insertPacket(Integer roomId, Packet newPacket) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.addPacket(newPacket);
            return true;
        } else
            return false;
    }

    static boolean deletePacket(Integer roomId, Packet newPacket) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.removePacket(newPacket);
            return true;
        } else
            return false;
    }

    static boolean updatePacket(Integer roomId, Packet newPacket) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.updatePacket(newPacket);
            return true;
        } else
            return false;
    }


}
