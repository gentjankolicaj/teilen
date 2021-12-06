package org.teilen.common.domain;

import lombok.Data;
import org.teilen.common.packet.base.Packet;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {
    private Integer id;
    private Integer ownerId;
    private String name;
    private static int contentCounter = 0;
    private List<Integer> clients;
    private List<RoomContent> contents;


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


    public Room(Integer id, Client firstClient, Client secondClient) {
        this.id = id;
        if (clients == null) {
            clients = new ArrayList<>();
        }
        clients.add(firstClient.getId());
        clients.add(secondClient.getId());
    }

    static Integer getContentId() {
        contentCounter++;
        return contentCounter;
    }

    public void addClient(Client client) {
        if (clients == null) {
            clients = new ArrayList<>();
            clients.add(client.getId());
        } else if (clients.size() == 0) {
            clients.add(client.getId());
        } else {
            boolean found = false;
            Integer newId = client.getId();
            for (int i = 0; i < clients.size(); i++) {
                Integer existingId = clients.get(i);
                if ((newId != null && existingId != null) && (newId.intValue() == existingId.intValue())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                clients.add(newId);
            }
        }
    }

    public void removeClient(Client client) {
        if (clients != null && clients.size() != 0) {
            Integer newId = client.getId();
            for (int i = 0; i < clients.size(); i++) {
                Integer existingId = clients.get(i);
                if ((newId != null && existingId != null) && (newId.intValue() == existingId.intValue())) {
                    clients.remove(i);
                    break;
                }
            }
        }
    }

    public RoomContent findRoomContent(Integer contentId) {
        for (int i = 0; i < contents.size(); i++) {
            RoomContent roomContent = contents.get(i);
            Integer actualContentId = roomContent.getId();
            if ((contentId != null && actualContentId != null) && (contentId.intValue() == actualContentId.intValue())) {
                return roomContent;
            }
        }
        return null;
    }

    public void addRoomContent(RoomContent roomContent) {
        if (contents == null) {
            contents = new ArrayList<>();
        }
        roomContent.setId(getContentId());
        contents.add(roomContent);
    }

    public void addRoomContent(Packet packet) {
        if (contents == null) {
            contents = new ArrayList<>();
        }
        RoomContent roomContent = new RoomContent(getContentId(), packet);
        contents.add(roomContent);
    }

    public void removeRoomContent(Integer contentId) {
        if (contents != null && contents.size() != 0) {
            for (int i = 0; i < contents.size(); i++) {
                RoomContent content = contents.get(i);
                Integer actualContentId = content.getId();
                if ((contentId != null && actualContentId != null) && (contentId.intValue() == actualContentId.intValue())) {
                    contents.remove(i);
                    break;
                }
            }
        }
    }

    public void updateRoomContent(Integer contentId, Packet packet) {
        if (contents == null) {
            contents = new ArrayList<>();
        } else if (contents.size() == 0) {
            RoomContent roomContent = new RoomContent(getContentId(), packet);
            contents.add(roomContent);
        } else {
            for (int i = 0; i < contents.size(); i++) {
                RoomContent roomContent = contents.get(i);
                Integer actualContentId = roomContent.getId();
                if ((contentId != null && actualContentId != null) && (contentId.intValue() == actualContentId.intValue())) {
                    contents.remove(i);

                    RoomContent updatedRoomContent = new RoomContent(contentId, packet);
                    contents.add(updatedRoomContent);
                    break;
                }
            }
        }
    }
}
