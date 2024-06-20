package org.teilen.desktop.engine;

import java.util.ArrayList;
import java.util.List;
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
import org.teilen.common.packet.media.MediaPacket;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnPacket;
import org.teilen.common.packet.meta.RoomOp;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.desktop.domain.ConnState;
import org.teilen.desktop.global.GlobalConfig;
import org.teilen.desktop.gui.ActivityPanel;
import org.teilen.desktop.gui.InfoPanel;
import org.teilen.desktop.queue.PacketQueue;
import org.teilen.desktop.repository.ClientRepository;
import org.teilen.desktop.repository.ConnRepository;
import org.teilen.desktop.repository.RoomRepository;
import org.teilen.desktop.util.LogUtil;


public class ActivityEngine implements Runnable {

  //Panels to reflect changes
  private ActivityPanel activityPanel;
  private InfoPanel infoPanel;

  public ActivityEngine() {
  }

  public void setActivityPanel(ActivityPanel activityPanel) {
    this.activityPanel = activityPanel;
  }

  public void setInfoPanel(InfoPanel infoPanel) {
    this.infoPanel = infoPanel;
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (activityPanel != null) {
          List<Packet> packets = PacketQueue.readIn(GlobalConfig.aePacketNumber);

          if (packets != null && packets.size() != 0) {
            //process in-queue packets and put them into out-queue
            processPackets(packets);
          }

          //Process changes at gui
          activityPanel.validateGui();
          infoPanel.validateGui();
        }
        Thread.sleep(GlobalConfig.aeThreadSleep);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  private void processPackets(List<Packet> packets) {
    List<Packet> processedPackets = new ArrayList<>();
    for (int i = 0; i < packets.size(); i++) {
      Packet packet = packets.get(i);
      if (packet instanceof MediaPacket) {
        //todo:
      } else {
        if (packet instanceof ConnPacket) {
          ConnPacket connPacket = (ConnPacket) packet;
          if (connPacket.connOp != null) {
            ConnRepository.updateConnState(connPacket.connOp);
            if (ConnRepository.findConnState().name().equals(ConnState.OFFLINE.name())) {
              ClientRepository.deleteAll();
            }
          }

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
            //todo
          } else if (roomPacket.roomOp.name().equals(RoomOp.ROOM_CREATE.name())) {
            if (body != null) {
              Content content = body.getContent();
              if (content != null) {
                if (content instanceof RoomWrapper) {
                  Room room = ((RoomWrapper) content).getRoom();
                  RoomRepository.insertRoom(room);
                  LogUtil.info("RoomOp.ROOM_CREATE : Room with id " + roomId
                      + " fully received from server.");
                } else {
                  LogUtil.error("RoomOp.ROOM_CREATE : room data not received.");
                }
              } else {
                LogUtil.error("RoomOp.ROOM_CREATE : room data not received.Body content null.");
              }
            } else {
              LogUtil.error("RoomOp.ROOM_CREATE : room data not received.Body null.");
            }

          } else if (roomPacket.roomOp.name().equals(RoomOp.ROOM_UPDATE.name())) {
            if (body != null) {
              Content content = body.getContent();
              if (content != null) {
                if (content instanceof RoomWrapper) {
                  Room room = ((RoomWrapper) content).getRoom();
                  RoomRepository.updateRoom(room);
                  LogUtil.info(
                      "RoomOp.UPDATE : Room with id " + roomId + " fully received from server.");

                } else if (content instanceof RoomClientsWrapper) {
                  Set<Integer> clientIds = ((RoomClientsWrapper) content).getClientIds();
                  Room room = RoomRepository.findRoomById(roomId);
                  if (room != null) {
                    room.updateClients(clientIds);
                    LogUtil.info(
                        "RoomOp.UPDATE : Room with id " + roomId + " clients ids updated.");

                  } else {
                    LogUtil.error(
                        "RoomOp.UPDATE : Room with id " + roomId + " clients ids not updated.");
                  }

                } else if (content instanceof RoomContentWrapper) {
                  Room room = RoomRepository.findRoomById(roomId);
                  if (room != null) {
                    List<RoomContent> roomContents = ((RoomContentWrapper) content).getRoomContents();
                    if (roomContents != null && roomContents.size() != 0) {
                      room.updateRoomContentsByGlobal(roomContents);
                      LogUtil.info("RoomOp.UPDATE : Room with id " + room.getId()
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
          if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
            Body body = clientPacket.getBody();
            Header header = clientPacket.getHeader();
            if (header != null) {
              if (body != null) {
                ClientInfoWrapper clientInfoWrapper = (ClientInfoWrapper) body.getContent();
                ClientRepository.insertClient(
                    new Client(clientPacket.getClientId(), clientInfoWrapper.getFirstname(),
                        clientInfoWrapper.getLastname()));
              } else {
                ClientRepository.insertClient(new Client(clientPacket.getClientId(), "~ ", "~ "));
              }
            } else {
              //to be decided if no header
            }
          } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
            Body body = clientPacket.getBody();
            Header header = clientPacket.getHeader();
            if (header != null) {
              //Packets with origin id -1 come from server and server assigns ids to clients
              Integer originId = header.getOriginId();
              if (originId != null && originId.intValue() == -1) {
                Integer clientId = clientPacket.getClientId();
                if (body != null) {
                  ClientInfoWrapper clientInfoWrapper = (ClientInfoWrapper) body.getContent();
                  ClientRepository.insertOwner(
                      new Client(clientId, clientInfoWrapper.getFirstname(),
                          clientInfoWrapper.getLastname()));
                } else {
                  ClientRepository.insertOwner(new Client(clientId, "~ ", "~ "));
                }
              } else {
                if (body != null) {
                  ClientInfoWrapper clientInfoWrapper = (ClientInfoWrapper) body.getContent();
                  ClientRepository.updateClient(
                      new Client(clientPacket.getClientId(), clientInfoWrapper.getFirstname(),
                          clientInfoWrapper.getLastname()));
                }
              }
            }
          } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
            ClientRepository.deleteClient(clientPacket.getClientId());
          }
        }
      }
    }
    PacketQueue.writeOut(processedPackets);
  }


}