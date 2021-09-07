package org.teilen_webcam.server;

import org.teilen_webcam.server.gui.ServerGuiFrame;

import java.awt.*;

public class ServerMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerGuiFrame frame = new ServerGuiFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
