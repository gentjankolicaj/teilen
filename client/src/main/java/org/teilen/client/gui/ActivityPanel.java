package org.teilen.client.gui;

import org.teilen.client.global.GlobalConfig;
import org.teilen.client.util.LogUtil;
import org.teilen.common.domain.Client;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnOp;
import org.teilen.common.packet.meta.ConnPacket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityPanel extends JPanel {
    private final ConnPanel connPanel;
    private final RoomPanel roomPanel;

    public ActivityPanel() {
        this.setPreferredSize(new Dimension(GlobalConfig.width, (int) (GlobalConfig.height * 0.55)));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new BorderLayout(0, 0));
        this.connPanel = new ConnPanel();
        this.add(connPanel, BorderLayout.WEST);

        this.roomPanel = new RoomPanel();
        this.add(roomPanel, BorderLayout.CENTER);
    }


    public void processGui(List<Packet> metas) {
        List<Packet> userMeta = getUserMeta(metas);
        List<Packet> connMeta = getConnMeta(metas);
        updateConnMeta(connMeta);
        updateUserMeta(userMeta);
    }

    private void updateConnMeta(List<Packet> connMeta) {
        if (connMeta != null && connMeta.size() != 0) {
            Packet lastConnPacket = connMeta.get(connMeta.size() - 1);
            ConnPacket connPacket = (ConnPacket) lastConnPacket;
            if (connPacket.connOp.name().equals(ConnOp.ON.name())) {
                connPanel.setServerImageGreen();
            } else if (connPacket.connOp.name().equals(ConnOp.OFF.name())) {
                connPanel.setServerImageRed();
                connPanel.userPanel.removeAllClients();
                connPanel.userScrollPane.validate();
            } else {
                //do other actions based on impl
            }
        }
    }


    private void updateUserMeta(List<Packet> userMeta) {
        if (userMeta != null) {
            UserPanel userPanel = connPanel.userPanel;
            for (Packet packet : userMeta) {
                ClientPacket clientPacket = (ClientPacket) packet;
                if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
                    userPanel.addClient(new Client(clientPacket.getClientId(), "Jame", "Doe"));
                } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
                    userPanel.removeClient(new Client(clientPacket.getClientId(), "Jame", "Doe"));
                }
            }
            connPanel.userScrollPane.validate();
            LogUtil.info("User-Panel packets : " + userMeta);
        }
    }

    private List<Packet> getUserMeta(List<Packet> metas) {
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
