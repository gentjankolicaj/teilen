package org.teilen.client.gui;

import org.teilen.client.global.GlobalConfig;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class ActivityPanel extends JPanel {
    private final RealtimePanel realtimePanel;
    private RoomPanel roomPanel;

    public ActivityPanel() {
        this.setPreferredSize(new Dimension(GlobalConfig.clientWidth, (int) (GlobalConfig.clientHeight * 0.55)));
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setLayout(new BorderLayout(0, 0));
        this.realtimePanel = new RealtimePanel(this);
        this.add(realtimePanel, BorderLayout.WEST);

        this.roomPanel = new RoomPanel();
        this.add(roomPanel, BorderLayout.CENTER);
    }


    public void openRoomWithClient(Integer clientId) {
        this.remove(roomPanel);

        RoomPanel newRoomPanel = new RoomPanel(clientId);
        this.roomPanel = newRoomPanel;
        this.add(roomPanel, BorderLayout.CENTER);
        this.validate();
    }


    public void validateGui() {
        this.realtimePanel.validateGui();
        if (roomPanel != null) {
            this.roomPanel.validateGui();
        }
    }


}
