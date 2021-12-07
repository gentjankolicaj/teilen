package org.teilen.server.gui;

import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.info.ClientInfo;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.server.global.GlobalConfig;
import org.teilen.server.util.LogUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ActivityPanel extends JSplitPane {
    private final JScrollPane userScrollPane;
    private final UserPanel userPanel;

    private final JScrollPane roomScrollPane;
    private final RoomPanel roomPanel;

    public ActivityPanel() {
        this.setPreferredSize(new Dimension((int) (GlobalConfig.width * 0.75), (int) (GlobalConfig.height * 0.5)));
        this.setContinuousLayout(true);
        this.setOrientation(SwingConstants.VERTICAL);

        this.userPanel = new UserPanel();
        this.userScrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        this.roomPanel = new RoomPanel();
        this.roomScrollPane = new JScrollPane(roomPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        this.setLeftComponent(userScrollPane);
        this.setRightComponent(roomScrollPane);
    }


    //Add room & validate scroll
    private void addRoom(Room room) {
        roomPanel.addRoom(room);
        roomScrollPane.validate();
    }

    //remove room & validate scroll
    private void removeRoom(Room room) {
        roomPanel.removeRoom(room);
        roomScrollPane.validate();
    }


    public void processGui(List<Packet> metas) {
        List<Packet> userMeta = getUserMeta(metas);
        List<Packet> roomMeta = getRoomMeta(metas);
        updateUserMeta(userMeta);
        updateRoomMeta(roomMeta);

    }

    private void updateRoomMeta(List<Packet> roomMeta) {
    }

    private void updateUserMeta(List<Packet> userMeta) {
        if (userMeta != null) {
            for (Packet packet : userMeta) {
                ClientPacket clientPacket = (ClientPacket) packet;
                if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
                    userPanel.addClient(new Client(clientPacket.getClientId(), "~ ", "~ "));
                } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
                    Body body = clientPacket.getBody();
                    if (body != null) {
                        ClientInfo clientInfo = (ClientInfo) body.getContent();
                        userPanel.updateClient(new Client(clientPacket.getClientId(), clientInfo.getFirstname(), clientInfo.getLastname()));
                    }
                } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
                    userPanel.removeClient(new Client(clientPacket.getClientId(), "~ ", "~ "));
                }
            }
            userScrollPane.validate();
            LogUtil.info("UserPanel packets : " + userMeta);
        }
    }

    private List<Packet> getUserMeta(List<Packet> metas) {
            List<Packet> userMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof ClientPacket) {
                    userMetas.add(packet);
                }
            }
            return userMetas;
    }

    private List<Packet> getRoomMeta(List<Packet> metas) {
            List<Packet> roomMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof RoomPacket) {
                    roomMetas.add(packet);
                }
            }
            return roomMetas;
    }


}

