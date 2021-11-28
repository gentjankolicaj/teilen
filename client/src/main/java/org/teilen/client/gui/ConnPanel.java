package org.teilen.client.gui;

import javax.swing.*;
import java.awt.*;

public class ConnPanel extends JPanel {
    final JScrollPane userScrollPane;
    final UserPanel userPanel;

    JLabel serverImage;

    public ConnPanel() {
        this.setLayout(new BorderLayout());
        this.setServerImageRed();

        this.userPanel = new UserPanel();
        this.userScrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(userScrollPane, BorderLayout.CENTER);

    }

    private static ImageIcon getServerRedImageIcon() {
        return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-server-red-40.png"));
    }

    private static ImageIcon getServerGreenImageIcon() {
        return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-server-green-40.png"));
    }

    public void setServerImageRed() {
        if (serverImage != null) {
            this.remove(serverImage);
            this.serverImage = new JLabel("~ disconnected");
            this.serverImage.setIcon(getServerRedImageIcon());
            this.add(serverImage, BorderLayout.NORTH);
            this.validate();
        } else {
            this.serverImage = new JLabel("~ disconnected");
            this.serverImage.setIcon(getServerRedImageIcon());
            this.add(serverImage, BorderLayout.NORTH);
        }
    }

    public void setServerImageGreen() {
        this.remove(serverImage);
        this.serverImage = new JLabel("~ connected");
        this.serverImage.setIcon(getServerGreenImageIcon());
        this.add(serverImage, BorderLayout.NORTH);
        this.validate();
    }

}
