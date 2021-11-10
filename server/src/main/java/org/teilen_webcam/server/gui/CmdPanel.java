package org.teilen_webcam.server.gui;

import org.teilen_webcam.server.engine.DiscoveryEngine;
import org.teilen_webcam.server.util.LogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CmdPanel extends JPanel {
    private final JButton closeGuiBtn;
    private final JButton shutdownBtn;
    private final JButton startBtn;
    private final List<Runnable> engines;

    public CmdPanel(List<Runnable> engines) {
        this.engines = engines;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.startBtn = new JButton("Start");
        this.shutdownBtn = new JButton("Shutdown");
        this.closeGuiBtn = new JButton("Exit");

        this.add(startBtn);
        this.add(shutdownBtn);
        this.add(closeGuiBtn);

        this.addBtnListeners();
    }


    public void addBtnListeners() {
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Start start socket server
                for (Runnable engine : engines) {
                    if (engine != null && engine instanceof DiscoveryEngine) {
                        DiscoveryEngine discoveryEngine = (DiscoveryEngine) engine;
                        try {
                            discoveryEngine.startSocketServer();
                        } catch (Exception e) {
                            LogUtil.error(e.getMessage());
                        }
                    }
                }
            }
        });

        shutdownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Start start socket server
                for (Runnable engine : engines) {
                    if (engine != null && engine instanceof DiscoveryEngine) {
                        DiscoveryEngine discoveryEngine = (DiscoveryEngine) engine;
                        try {
                            discoveryEngine.shutdownSocketServer();
                        } catch (Exception e) {
                            LogUtil.error(e.getMessage());
                        }
                    }
                }
            }
        });

        closeGuiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }


}
