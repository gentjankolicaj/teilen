package org.teilen.server.gui;

import org.teilen.common.domain.Room;
import org.teilen.common.domain.User;

import javax.swing.*;


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
    public void addRoom(Room room) {
        roomPanel.addRoom(room);
        roomScrollPane.validate();
    }

    //remove room & validate scroll
    public void removeRoom(Room room) {
        roomPanel.removeRoom(room);
        roomScrollPane.validate();
    }

    //Add user & validate scroll
    public void addUser(User user) {
        userPanel.addUser(user);
        userScrollPane.validate();
    }

    //remove user & validate scroll
    public void removeUser(User user) {
        userPanel.removeUser(user);
        userScrollPane.validate();
    }
}

