package org.teilen.client.engine;

import org.teilen.client.domain.GuiPanel;
import org.teilen.client.gui.ActivityPanel;
import org.teilen.client.gui.InfoPanel;
import org.teilen.client.queue.PacketQueue;
import org.teilen.client.repository.ClientRepository;
import org.teilen.client.repository.ConnRepository;
import org.teilen.common.domain.Client;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.info.ClientInfo;
import org.teilen.common.packet.media.*;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnPacket;
import org.teilen.common.packet.meta.RoomPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ActivityEngine implements Runnable {
    private static final int threadSleep = 200; //millis
    private static final int packetNumber = 5;

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
                    List<Packet> packets = PacketQueue.readIn(packetNumber);
                    if (packets != null && packets.size() != 0) {
                        //process in-queue packets and put them into out-queue
                        Set<GuiPanel> guiPanelSet = processPackets(packets);

                        //Process changes at gui
                        if (guiPanelSet != null) {
                            for (GuiPanel guiPanel : guiPanelSet) {
                                if (guiPanel.name().equals(GuiPanel.ACTIVITY.name())) {
                                    activityPanel.validateActivityGui();
                                    infoPanel.validateOwnerGui();
                                } else if (guiPanel.name().equals(GuiPanel.INFO.name())) {
                                    infoPanel.validateGui();
                                }
                            }
                        }
                    }
                } else {
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
                Thread.sleep(threadSleep);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private Set<GuiPanel> processPackets(List<Packet> packets) {
        Set<GuiPanel> guiPanelSet = new TreeSet<>();
        List<Packet> processedPackets = new ArrayList<>();
        for (int i = 0; i < packets.size(); i++) {
            Packet packet = packets.get(i);
            if (packet instanceof MediaPacket) {
                if (packet instanceof TextPacket) {

                } else if (packet instanceof SoundPacket) {

                } else if (packet instanceof VideoPacket) {

                } else if (packet instanceof FilePacket) {

                }
            } else {
                if (packet instanceof ConnPacket) {
                    ConnPacket connPacket = (ConnPacket) packet;
                    guiPanelSet.add(GuiPanel.ACTIVITY);
                    if (connPacket.connOp != null) {
                        ConnRepository.updateConnState(connPacket.connOp);
                    }

                } else if (packet instanceof RoomPacket) {

                } else if (packet instanceof ClientPacket) {
                    ClientPacket clientPacket = (ClientPacket) packet;
                    guiPanelSet.add(GuiPanel.ACTIVITY);
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
                        Body body = clientPacket.getBody();
                        Header header = clientPacket.getHeader();
                        if (header != null) {
                            if (body != null) {
                                ClientInfo clientInfo = (ClientInfo) body.getContent();
                                ClientRepository.insertClient(new Client(clientPacket.getClientId(), clientInfo.getFirstname(), clientInfo.getLastname()));
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
                                    ClientInfo clientInfo = (ClientInfo) body.getContent();
                                    ClientRepository.insertOwner(new Client(clientId, clientInfo.getFirstname(), clientInfo.getLastname()));
                                } else {
                                    ClientRepository.insertOwner(new Client(clientId, "~ ", "~ "));
                                }
                            } else {
                                if (body != null) {
                                    ClientInfo clientInfo = (ClientInfo) body.getContent();
                                    ClientRepository.updateClient(new Client(clientPacket.getClientId(), clientInfo.getFirstname(), clientInfo.getLastname()));
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
        return guiPanelSet;
    }


}