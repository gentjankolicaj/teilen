package org.teilen.common.domain;

import lombok.Data;
import org.teilen.common.packet.Packet;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {
    private Integer id;
    private Integer ownerId;
    private String name;
    private List<User> users;
    private List<Packet> packets;


    public Room(Integer id) {
        this.id = id;
    }

    public Room(Integer id, Integer ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Room(Integer id, User firstUser, User secondUser) {
        this.id = id;
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(firstUser);
        users.add(secondUser);
    }

    public void addUser(User newUser) {
        if (users == null) {
            users = new ArrayList<>();
            users.add(newUser);
        } else if (users.size() == 0) {
            users.add(newUser);
        } else {
            boolean found = false;
            for (int i = 0; i < users.size(); i++) {
                User tmp = users.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = newUser.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                users.add(newUser);
            }
        }
    }

    public void updateUser(User newUser) {
        if (users == null) {
            users = new ArrayList<>();
            users.add(newUser);
        } else if (users.size() == 0) {
            users.add(newUser);
        } else {
            for (int i = 0; i < users.size(); i++) {
                User tmp = users.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = newUser.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    users.remove(i);
                    users.add(newUser);
                    break;
                }
            }
        }
    }

    public void removeUser(User newUser) {
        if (users != null && users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                User tmp = users.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = newUser.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    users.remove(i);
                    break;
                }
            }
        }
    }


    public void addPacket(Packet packet) {
        if (packets == null) {
            packets = new ArrayList<>();
        }
        packets.add(packet);
    }

    public void removePacket(Packet newPacket) {
        if (packets != null && packets.size() != 0) {
            for (int i = 0; i < packets.size(); i++) {
                Packet tmp = packets.get(i);
                Integer tmpPacketId = tmp.getOriginId();
                Integer packetId = newPacket.getOriginId();
                if ((tmpPacketId != null && packetId != null) && (tmpPacketId.intValue() == packetId.intValue())) {
                    packets.remove(i);
                    break;
                }
            }
        }
    }


    public void updatePacket(Packet newPacket) {
        if (packets == null) {
            packets = new ArrayList<>();
            packets.add(newPacket);
        } else if (packets.size() == 0) {
            packets.add(newPacket);
        } else {
            for (int i = 0; i < packets.size(); i++) {
                Packet tmp = packets.get(i);
                Integer tmpPacketId = tmp.getOriginId();
                Integer packetId = newPacket.getOriginId();
                if ((tmpPacketId != null && packetId != null) && (tmpPacketId.intValue() == packetId.intValue())) {
                    packets.remove(i);
                    packets.add(newPacket);
                    break;
                }
            }
        }
    }
}
