package org.teilen_webcam.client;

import org.teilen_webcam.client.gui.ClientGuiFrame;

import java.awt.*;

public class ClientMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientGuiFrame frame = new ClientGuiFrame();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
