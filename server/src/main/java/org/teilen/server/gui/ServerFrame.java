package org.teilen.server.gui;

import org.teilen.server.engine.ActivityEngine;
import org.teilen.server.engine.DiscoveryEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerFrame extends JFrame {

    private final JPanel contentPane;
    private final InfoPanel infoPanel;
    private final ActivityPanel activityPanel;
    private final JPanel cmdPanel;


    public ServerFrame(ActivityEngine activityEngine, DiscoveryEngine discoveryEngine) {
        this.setTitle("Teilen-server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 900, 750);

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        this.infoPanel = new InfoPanel();
        this.activityPanel = new ActivityPanel();
        this.cmdPanel = new CmdPanel(discoveryEngine);

        this.contentPane.add(infoPanel, BorderLayout.NORTH);
        this.contentPane.add(activityPanel, BorderLayout.CENTER);
        this.contentPane.add(cmdPanel, BorderLayout.SOUTH);

        this.setVisible(true);

        //Set panels to be used by activity engine
        activityEngine.setActivityPanel(activityPanel);
    }
}
