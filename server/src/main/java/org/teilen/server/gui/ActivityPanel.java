package org.teilen.server.gui;

import org.teilen.common.domain.Room;
import org.teilen.common.domain.User;
import org.teilen.common.packet.Packet;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.common.packet.meta.UserOp;
import org.teilen.common.packet.meta.UserPacket;
import org.teilen.server.util.LogUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class ActivityPanel extends JSplitPane {
    private final JScrollPane userScrollPane;
    private final UserPanel userPanel;

    private final JScrollPane roomScrollPane;
    private final RoomPanel roomPanel;

    public ActivityPanel() {
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

    //Add user & validate scroll
    private void addUser(User user) {
        userPanel.addUser(user);
        userScrollPane.validate();
    }

    //remove user & validate scroll
    private void removeUser(User user) {
        userPanel.removeUser(user);
        userScrollPane.validate();
    }

    public void updateMeta(List<Packet> metas) {
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
                UserPacket userPacket = (UserPacket) packet;
                if (userPacket.userOp.name().equals(UserOp.USER_CREATE.name())) {
                    userPanel.addUser(new User(userPacket.id, "Jame", "Doe"));
                } else if (userPacket.userOp.name().equals(UserOp.USER_DELETE.name())) {
                    userPanel.removeUser(new User(userPacket.id, "Jame", "Doe"));
                }
            }
            userScrollPane.validate();
            LogUtil.info("User panel updated.");
        }
    }

    private List<Packet> getUserMeta(List<Packet> metas) {
        if (metas == null || metas.size() == 0)
            return null;
        else {
            List<Packet> userMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof UserPacket) {
                    userMetas.add(packet);
                }
            }
            return userMetas;
        }
    }

    private List<Packet> getRoomMeta(List<Packet> metas) {
        if (metas == null || metas.size() == 0)
            return null;
        else {
            List<Packet> roomMetas = new ArrayList<>();
            for (Packet packet : metas) {
                if (packet instanceof RoomPacket) {
                    roomMetas.add(packet);
                }
            }
            return roomMetas;
        }
    }


}

