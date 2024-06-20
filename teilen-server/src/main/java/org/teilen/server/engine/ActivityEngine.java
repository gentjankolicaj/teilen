package org.teilen.server.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Content;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.base.wrapper.ClientInfoWrapper;
import org.teilen.common.packet.base.wrapper.RoomClientsWrapper;
import org.teilen.common.packet.base.wrapper.RoomContentWrapper;
import org.teilen.common.packet.base.wrapper.RoomWrapper;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnPacket;
import org.teilen.common.packet.meta.RoomOp;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.server.gui.ActivityPanel;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.repository.ClientRepository;
import org.teilen.server.repository.RoomRepository;
import org.teilen.server.util.LogUtil;

public class ActivityEngine extends Thread {

  private static final int threadSleep = 200; //millis
  private static final int packetNumber = 5;
  private ActivityPanel activityPanel;

  public ActivityEngine() {
    super("ActivityEngine");
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (activityPanel != null) {
          LogUtil.info("AE & GUI : processing started.");
          processWithGui();
          LogUtil.info("AE & GUI : processing finished.");
          Thread.sleep(threadSleep);
        } else {
          LogUtil.info("AE : processing started.");
          processWithoutGui();
          LogUtil.info("AE : processing finished.");
          long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
          Thread.sleep(otherSleep);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void setActivityPanel(ActivityPanel activityPanel) {
    this.activityPanel = activityPanel;
  }


  private void processWithGui() {
    try {
      List<Packet> packets = PacketQueue.readIn(packetNumber);
      if (packets != null && packets.size() != 0) {
        //process in-queue packets and put them into out-queue
        processPackets(packets);
        activityPanel.validateGui();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void processWithoutGui() {
    try {
      List<Packet> packets = PacketQueue.readIn(packetNumber);
      if (packets != null && packets.size() != 0) {
        //process in-queue packets and put them into out-queue
        processPackets(packets);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void processPackets(List<Packet> packets) {
    Map<Integer, List<Packet>> clientPacketMap = new HashMap<>();
    Map<Integer, List<Packet>> allClientPacketMap = new HashMap<>();

    for (int i = 0; i < packets.size(); i++) {
      Packet packet = packets.get(i);

      if (packet instanceof ConnPacket) {

      } else if (packet instanceof RoomPacket) {
        RoomPacket roomPacket = (RoomPacket) packet;
        Integer roomId = roomPacket.getRoomId();
        Header header = roomPacket.getHeader();
        Body body = roomPacket.getBody();
        Integer originId = null;
        if (header != null) {
          originId = header.getOriginId();
        }
        if (roomPacket.roomOp.name().equals(RoomOp.ROOM_READ.name())) {
          if (roomId != null) {
            Room room = RoomRepository.findRoomById(roomId);
            if (room != null) {
              RoomPacket rRoomPacket = new RoomPacket(new Header(-1, originId),
                  new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);
              updateClientPacketMap(clientPacketMap, originId, rRoomPacket);
            }

          } else {
            if (body != null) {
              Content content = body.getContent();
              if (content instanceof RoomClientsWrapper) {
                Set<Integer> clientIds = ((RoomClientsWrapper) content).getClientIds();
                if (clientIds != null && clientIds.size() != 0) {
                  Room room = RoomRepository.findRoomByClientIds(clientIds);
                  if (room != null) {
                    roomId = room.getId();
                    RoomPacket rRoomPacket = new RoomPacket(new Header(-1, originId),
                        new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);
                    updateClientPacketMap(clientPacketMap, originId, rRoomPacket);
                  } else {
                    room = RoomRepository.createRoomByServer(clientIds);
                    RoomPacket rRoomPacket = new RoomPacket(new Header(-1, originId),
                        new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);

                    //sent to requesting client
                    updateClientPacketMap(clientPacketMap, originId, rRoomPacket);

                    //sent to all clientIds
                    for (Integer tmpClientId : clientIds) {
                      RoomPacket tmpRoomPacket = new RoomPacket(new Header(-1, tmpClientId),
                          new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);
                      updateClientPacketMap(clientPacketMap, tmpClientId, tmpRoomPacket);
                    }
                  }
                }
              }
            }
          }
        } else if (roomPacket.roomOp.name().equals(RoomOp.ROOM_CREATE.name())) {
          //create room with multiple clients
          if (body != null) {
            Content content = body.getContent();
            if (content instanceof RoomClientsWrapper) {
              Set<Integer> clientIds = ((RoomClientsWrapper) content).getClientIds();
              if (clientIds != null && clientIds.size() != 0) {
                Room room = RoomRepository.createRoomByClient(originId, clientIds);

                RoomPacket cRoomPacket = new RoomPacket(new Header(originId, originId),
                    new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);
                //sent to requesting client
                updateClientPacketMap(clientPacketMap, originId, cRoomPacket);

                //sent to all clientIds
                for (Integer tmpClientId : clientIds) {
                  RoomPacket tmpRoomPacket = new RoomPacket(new Header(originId, tmpClientId),
                      new Body(null, new RoomWrapper(room)), roomId, RoomOp.ROOM_CREATE);
                  updateClientPacketMap(clientPacketMap, tmpClientId, tmpRoomPacket);
                }
              }
            }
          }

        } else if (roomPacket.roomOp.name().equals(RoomOp.ROOM_UPDATE.name())) {
          if (body != null) {
            Content content = body.getContent();
            if (content != null) {
              if (content instanceof RoomClientsWrapper) {
                Set<Integer> clientIds = ((RoomClientsWrapper) content).getClientIds();
                Room room = RoomRepository.findRoomById(roomId);
                if (room != null) {
                  if (room.getOwnerId().intValue() == originId.intValue()) {
                    room.updateClients(clientIds);

                    //sent to all clientIds besides original client
                    for (Integer clientId : clientIds) {
                      if (originId.intValue() != clientId.intValue()) {
                        RoomPacket tmpRoomPacket = new RoomPacket(
                            new Header(originId, clientId),
                            new Body(null, new RoomClientsWrapper(clientIds)), roomId,
                            RoomOp.ROOM_UPDATE);
                        updateClientPacketMap(clientPacketMap, clientId, tmpRoomPacket);
                      }
                    }
                    LogUtil.info(
                        "RoomOp.UPDATE : Room with id " + room.getId()
                            + " clients ids updated.");
                  } else {
                    LogUtil.warn("RoomOp.UPDATE : Room with id " + roomId
                        + " clients ids not updated.Room owner " + room.getOwnerId()
                        + ", originId "
                        + originId);
                  }
                } else {
                  LogUtil.error("RoomOp.UPDATE : Room with id " + roomId
                      + " clients ids not updated.Room not found.");
                }

              } else if (content instanceof RoomContentWrapper) {
                Room room = RoomRepository.findRoomById(roomId);
                if (room != null) {
                  List<RoomContent> roomContents = ((RoomContentWrapper) content).getRoomContents();
                  Set<Integer> clientIds = room.getClients();
                  if (roomContents != null && roomContents.size() != 0) {
                    room.updateRoomContentsByGlobal(roomContents);

                    //sent to all clientIds besides original client
                    for (Integer clientId : clientIds) {
                      if (originId.intValue() != clientId.intValue()) {
                        RoomPacket tmpRoomPacket = new RoomPacket(
                            new Header(originId, clientId),
                            new Body(null, new RoomContentWrapper(roomContents)), roomId,
                            RoomOp.ROOM_UPDATE);
                        updateClientPacketMap(clientPacketMap, clientId, tmpRoomPacket);
                      }
                    }
                    LogUtil.info(
                        "RoomOp.UPDATE : Room with id " + room.getId()
                            + " room content updated.");
                  }
                } else {
                  LogUtil.error("RoomOp.UPDATE : Room with id " + room.getId()
                      + " room content not updated.");
                }

              }
            }
          }

        } else if (roomPacket.roomOp.name().equals(RoomOp.ROOM_DELETE.name())) {
          RoomRepository.deleteRoom(roomId);
        }

      } else if (packet instanceof ClientPacket) {
        ClientPacket clientPacket = (ClientPacket) packet;
        Integer clientId = clientPacket.getClientId();
        if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
          //create 2 packets
          //1 update existing user with id
          //2 update other users with new user
          // -1 => teilen-server
          // 0 => All users
          // 1-n => specific user
          //origin -1 => origin :teilen-server,
          //destination 0 => all users

          //Packet to be sent to specific client
          ClientPacket uClientPacket = new ClientPacket(new Header(-1, clientId),
              clientPacket.getClientId(), ClientOp.CLIENT_UPDATE);
          updateClientPacketMap(clientPacketMap, clientId, uClientPacket);

          //Packets to be sent to specific client & client to be updated with existing clients
          List<Packet> cClientPackets = getExistingClientPackets(clientId);
          updateClientPacketMap(clientPacketMap, clientId, cClientPackets);

          //Packet to be sent to all clients
          ClientPacket cClientPacket = new ClientPacket(new Header(-1, 0),
              clientPacket.getClientId(), ClientOp.CLIENT_CREATE);
          updateAllClientPacketMap(allClientPacketMap, clientId, cClientPacket);

          //Finally, add new client on repository
          Client client = new Client(clientId);
          ClientRepository.insertClient(client);

        } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
          //origin : user
          //Destination : all users
          ClientInfoWrapper clientInfoWrapper = (ClientInfoWrapper) clientPacket.getBody()
              .getContent();
          Client client = new Client(clientId, clientInfoWrapper);
          ClientRepository.updateClient(client);

          //Packet to be sent to all clients
          ClientPacket uClientPacket = new ClientPacket(new Header(clientId, 0),
              new Body(null, clientInfoWrapper), clientPacket.getClientId(),
              ClientOp.CLIENT_UPDATE);
          updateAllClientPacketMap(allClientPacketMap, clientId, uClientPacket);

        } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
          //origin : teilen-server
          //Destination : all users
          ClientRepository.deleteClient(clientId);
          ClientPacket dClientPacket = new ClientPacket(new Header(-1, 0), clientId,
              ClientOp.CLIENT_DELETE);
          updateAllClientPacketMap(allClientPacketMap, clientId, dClientPacket);
        }
      }

    }
    Map<Integer, List<Packet>> processedMap = getProcessedMap(clientPacketMap, allClientPacketMap);
    PacketQueue.writeOut(processedMap);
  }

  private List<Packet> getExistingClientPackets(Integer clientId) {
    List<Client> clients = ClientRepository.findAllList();
    if (clients != null && clients.size() != 0) {
      List<Packet> clientPackets = new ArrayList<>();
      for (int i = 0; i < clients.size(); i++) {
        Client existingClient = clients.get(i);
        ClientPacket clientPacket = new ClientPacket(new Header(-1, clientId), new Body(null,
            new ClientInfoWrapper(existingClient.getFirstname(), existingClient.getLastname())),
            existingClient.getId(), ClientOp.CLIENT_CREATE);
        clientPackets.add(clientPacket);
      }
      return clientPackets;
    } else {
      return null;
    }
  }

  private void updateClientPacketMap(Map<Integer, List<Packet>> packetMap, Integer clientId,
      Packet packet) {
    List<Packet> clientPackets = packetMap.get(clientId);
    if (clientPackets == null) {
      clientPackets = new ArrayList<>();
      clientPackets.add(packet);
      packetMap.put(clientId, clientPackets);
    } else {
      clientPackets.add(packet);
    }
  }

  private void updateClientPacketMap(Map<Integer, List<Packet>> packetMap, Integer clientId,
      List<Packet> packets) {
    if (packets != null) {
      List<Packet> clientPackets = packetMap.get(clientId);
      if (clientPackets == null) {
        packetMap.put(clientId, packets);
      } else {
        clientPackets.addAll(packets);
      }
    }
  }

  private void updateAllClientPacketMap(Map<Integer, List<Packet>> packetMap, Integer clientId,
      Packet packet) {
    List<Packet> nonClientPackets = packetMap.get(clientId);
    if (nonClientPackets == null) {
      nonClientPackets = new ArrayList<>();
      nonClientPackets.add(packet);
      packetMap.put(clientId, nonClientPackets);
    } else {
      nonClientPackets.add(packet);
    }
  }

  private Map<Integer, List<Packet>> getProcessedMap(
      Map<Integer, List<Packet>> specificClientPacketMap,
      Map<Integer, List<Packet>> allClientPacketMap) {
    Map<Integer, List<Packet>> processedMap = new HashMap<>();
    Map<Integer, Client> clients = ClientRepository.findAll();
    if (clients != null) {
      //Sent to all clients but expel only client with same id
      for (Map.Entry<Integer, Client> clientEntry : clients.entrySet()) {
        Integer clientId = clientEntry.getKey();
        for (Map.Entry<Integer, List<Packet>> packetEntry : allClientPacketMap.entrySet()) {
          Integer nonClientId = packetEntry.getKey();
          List<Packet> nonClientPackets = packetEntry.getValue();
          if ((clientId != null && nonClientId != null) && (clientId.intValue()
              != nonClientId.intValue())) {
            updateProcessedMap(processedMap, clientId, nonClientPackets);
          }
        }
      }

      //Sent to clients with same id
      for (Map.Entry<Integer, List<Packet>> specificEntry : specificClientPacketMap.entrySet()) {
        Integer clientId = specificEntry.getKey();
        List<Packet> clientPackets = specificEntry.getValue();
        boolean notFound = true;
        for (Map.Entry<Integer, List<Packet>> processedEntry : processedMap.entrySet()) {
          Integer currentClientId = processedEntry.getKey();
          if ((clientId != null && currentClientId != null) && (clientId.intValue()
              == currentClientId.intValue())) {
            updateProcessedMap(processedMap, clientId, clientPackets);
            notFound = false;
          }
        }
        if (notFound) {
          processedMap.put(clientId, clientPackets);
        }
      }
    }
    return processedMap;
  }

  private void updateProcessedMap(Map<Integer, List<Packet>> processedMap, Integer clientId,
      List<Packet> newPackets) {
    List<Packet> packets = processedMap.get(clientId);
    if (packets == null) {
      processedMap.put(clientId, newPackets);
    } else {
      packets.addAll(newPackets);
    }
  }


}
