package org.teilen.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.Data;

@Data
public class Room implements Serializable {

  static int roomIdCounter = 0;
  static int globalIdCounter = 0;
  static int localIdCounter = 0;

  private Integer id;
  private Integer ownerId;
  private String roomName;

  private Set<Integer> clients;
  private List<RoomContent> contents;

  public Room(Integer ownerId) {
    this.id = getRoomId();
    this.ownerId = ownerId;
  }

  public Room(Integer ownerId, String roomName) {
    this.id = getRoomId();
    this.ownerId = ownerId;
    this.roomName = roomName;
  }


  public Room(Client firstClient, Client secondClient) {
    this.id = getRoomId();
    if (clients == null) {
      clients = new TreeSet<>();
    }
    clients.add(firstClient.getId());
    clients.add(secondClient.getId());
  }

  static Integer getGlobalId() {
    globalIdCounter++;
    return globalIdCounter;
  }

  static Integer getLocalId() {
    localIdCounter++;
    return localIdCounter;
  }

  static Integer getRoomId() {
    roomIdCounter++;
    return roomIdCounter;
  }


  public void addClient(Client client) {
    if (clients == null) {
      clients = new TreeSet<>();
    }
    clients.add(client.getId());

  }

  public void updateClients(Set<Integer> clientIds) {
    if (clientIds != null && clientIds.size() != 0) {
      clients.addAll(clientIds);
    }
  }

  public boolean isClient(Integer clientId) {
    if (clients == null) {
      return false;
    } else {
      return clients.contains(clientId);
    }
  }

  public void removeClient(Client client) {
    if (clients != null && clients.size() != 0 && client != null) {
      clients.remove(client.getId());
    }
  }


  public RoomContent findRoomContentByGlobalId(Integer genericId) {
    for (int i = 0; i < contents.size(); i++) {
      RoomContent roomContent = contents.get(i);
      Integer actualId = roomContent.getGlobalId();
      if ((genericId != null && actualId != null) && (actualId.intValue()
          == genericId.intValue())) {
        return roomContent;
      }
    }
    return null;
  }

  public RoomContent findRoomContentByLocalId(Integer localId) {
    for (int i = 0; i < contents.size(); i++) {
      RoomContent roomContent = contents.get(i);
      Integer actualId = roomContent.getGlobalId();
      if ((actualId != null && localId != null) && (actualId.intValue() == localId.intValue())) {
        return roomContent;
      }
    }
    return null;
  }

  public void addRoomContentByGlobal(RoomContent roomContent) {
    if (contents == null) {
      contents = new ArrayList<>();
    }
    contents.add(roomContent);
  }

  public void addRoomContentByLocal(RoomContent roomContent) {
    if (contents == null) {
      contents = new ArrayList<>();
    }
    roomContent.setLocalId(getLocalId());
    contents.add(roomContent);
  }


  public void removeRoomContentByGlobal(Integer globalId) {
    if (contents != null && contents.size() != 0) {
      for (int i = 0; i < contents.size(); i++) {
        RoomContent content = contents.get(i);
        Integer actualId = content.getGlobalId();
        if ((actualId != null && globalId != null) && (actualId.intValue()
            == globalId.intValue())) {
          contents.remove(i);
          break;
        }
      }
    }
  }

  public void removeRoomContentByLocal(Integer localId) {
    if (contents != null && contents.size() != 0) {
      for (int i = 0; i < contents.size(); i++) {
        RoomContent content = contents.get(i);
        Integer actualId = content.getLocalId();
        if ((actualId != null && localId != null) && (actualId.intValue() == localId.intValue())) {
          contents.remove(i);
          break;
        }
      }
    }
  }

  public void updateRoomContentByGlobal(RoomContent roomContent) {
    if (contents == null) {
      contents = new ArrayList<>();
      contents.add(roomContent);
    } else if (contents.size() == 0) {
      contents.add(roomContent);
    } else {
      Integer globalId = roomContent.getGlobalId();
      for (int i = 0; i < contents.size(); i++) {
        RoomContent tmpRoomContent = contents.get(i);
        Integer tmpGlobalId = tmpRoomContent.getGlobalId();
        if ((tmpGlobalId != null && globalId != null) && (tmpGlobalId.intValue()
            == globalId.intValue())) {
          contents.add(i, roomContent);
          break;
        }
      }
    }
  }

  public void updateRoomContentByLocal(RoomContent roomContent) {
    if (contents == null) {
      contents = new ArrayList<>();
      contents.add(roomContent);
    } else if (contents.size() == 0) {
      contents.add(roomContent);
    } else {
      Integer localId = roomContent.getGlobalId();
      for (int i = 0; i < contents.size(); i++) {
        RoomContent tmpRoomContent = contents.get(i);
        Integer tmpLocalId = tmpRoomContent.getGlobalId();
        if ((tmpLocalId != null && localId != null) && (tmpLocalId.intValue()
            == localId.intValue())) {
          contents.add(i, roomContent);
          break;
        }
      }
    }
  }

  public void updateRoomContentsByGlobal(List<RoomContent> roomContents) {
    if (contents == null) {
      contents = new ArrayList<>();
      contents.addAll(roomContents);
    } else if (contents.size() == 0) {
      contents.addAll(roomContents);
    } else {
      List<RoomContent> newContent = new ArrayList<>();
      for (int i = 0; i < roomContents.size(); i++) {
        RoomContent roomContent = roomContents.get(i);
        Integer globalId = roomContent.getGlobalId();
        int foundIndex = -1;
        for (int j = 0; j < contents.size(); j++) {
          RoomContent tmpRoomContent = contents.get(j);
          Integer tmpGlobalId = tmpRoomContent.getGlobalId();
          if ((tmpGlobalId != null && globalId != null) && (tmpGlobalId.intValue()
              == globalId.intValue())) {
            foundIndex = j;
            break;
          }
        }
        if (foundIndex != -1) {
          contents.add(foundIndex, roomContent);
        } else {
          newContent.add(roomContent);
        }
      }
      contents.addAll(newContent);
    }
  }
}
