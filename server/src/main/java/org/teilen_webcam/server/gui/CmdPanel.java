package org.teilen_webcam.server.gui;

import javax.swing.*;
import java.awt.*;

public class CmdPanel extends JPanel {
    private final JButton closeGuiBtn;
    private final JButton shutdownBtn;
    private final JButton startBtn;

    public CmdPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        startBtn = new JButton("Start server");
        add(startBtn);

        shutdownBtn = new JButton("Shutdown server");
        add(shutdownBtn);

        closeGuiBtn = new JButton("Close gui");
        add(closeGuiBtn);
    }
}
