package org.teilen.client.gui;

import javax.swing.*;
import java.awt.*;


public class RoomPanel extends JPanel {
    HeaderPanel headerPanel;
    BodyPanel bodyPanel;
    FooterPanel footerPanel;

    JLabel noChatLbl;


    public RoomPanel() {
        this.setLayout(new BorderLayout(0, 0));

        this.noChatLbl = new JLabel("    No chat window opened , click an available user and start chatting...");
        this.add(noChatLbl, BorderLayout.CENTER);
    }

    public RoomPanel(Integer clientId) {
        if (clientId != null) {
            this.setLayout(new BorderLayout(0, 0));

            this.headerPanel = new HeaderPanel(clientId);
            this.bodyPanel = new BodyPanel(clientId);
            this.footerPanel = new FooterPanel(clientId);

            this.add(headerPanel, BorderLayout.NORTH);
            this.add(bodyPanel, BorderLayout.CENTER);
            this.add(footerPanel, BorderLayout.SOUTH);
        } else {
            this.setLayout(new BorderLayout(0, 0));

            this.noChatLbl = new JLabel("    No chat window opened , click an available user and start chatting...");
            this.add(noChatLbl, BorderLayout.CENTER);
        }
    }


}



