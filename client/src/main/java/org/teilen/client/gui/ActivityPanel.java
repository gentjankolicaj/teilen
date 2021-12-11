package org.teilen.client.gui;

import org.teilen.client.global.GlobalConfig;
import org.teilen.client.util.LogUtil;
import org.teilen.common.domain.Client;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.info.ClientInfo;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnOp;
import org.teilen.common.packet.meta.ConnPacket;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityPanel extends JPanel {
    private final RealtimePanel realtimePanel;
    private RoomPanel roomPanel;

    public ActivityPanel() {
        this.setPreferredSize(new Dimension(GlobalConfig.width, (int) (GlobalConfig.height * 0.55)));
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setLayout(new BorderLayout(0, 0));
        this.realtimePanel = new RealtimePanel(this);
        this.add(realtimePanel, BorderLayout.WEST);

        this.roomPanel = new RoomPanel();
        this.add(roomPanel, BorderLayout.CENTER);
    }


    public void openRoomWithClient(Integer clientId) {
        RoomPanel newRoomPanel = new RoomPanel(clientId);
        this.remove(roomPanel);
        this.roomPanel = newRoomPanel;
        this.add(roomPanel, BorderLayout.CENTER);
        this.validate();
    }


    public void processGui(List<Packet> packets) {
        List<Packet> clientMeta = getClientMeta(packets);
        List<Packet> connMeta = getConnMeta(packets);
        updateConnMeta(connMeta);
        updateClientMeta(clientMeta);
    }

    private void updateConnMeta(List<Packet> connMeta) {
        if (connMeta != null && connMeta.size() != 0) {
            Packet lastConnPacket = connMeta.get(connMeta.size() - 1);
            ConnPacket connPacket = (ConnPacket) lastConnPacket;
            if (connPacket.connOp.name().equals(ConnOp.ON.name())) {
                realtimePanel.setServerImageGreen();
            } else if (connPacket.connOp.name().equals(ConnOp.OFF.name())) {
                realtimePanel.setServerImageRed();
                realtimePanel.userPanel.removeAllClients();
                realtimePanel.userScrollPane.validate();
            } else {
                //do other actions based on impl
            }
        }
    }


    private void updateClientMeta(List<Packet> userMeta) {
        if (userMeta != null) {
            UserPanel userPanel = realtimePanel.userPanel;
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
            realtimePanel.userScrollPane.validate();
            LogUtil.info("UserPanel packets : " + userMeta);
        }
    }

    private List<Packet> getClientMeta(List<Packet> metas) {
        if (metas == null || metas.size() == 0)
            return null;
        else {
            List<Packet> userMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof ClientPacket) {
                    userMetas.add(packet);
                }
            }
            return userMetas;
        }
    }


    private List<Packet> getConnMeta(List<Packet> metas) {
        if (metas == null || metas.size() == 0)
            return null;
        else {
            List<Packet> connMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof ConnPacket) {
                    connMetas.add(packet);
                }
            }
            return connMetas;
        }
    }

}
