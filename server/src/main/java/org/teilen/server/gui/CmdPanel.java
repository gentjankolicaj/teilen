package org.teilen.server.gui;

import org.teilen.server.engine.IOEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CmdPanel extends JPanel {
    private final JButton closeGuiBtn;
    private final JButton shutdownBtn;
    private final JButton startBtn;
    private final IOEngine ioEngine;

    public CmdPanel(IOEngine ioEngine) {
        this.ioEngine = ioEngine;

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
                if (ioEngine != null) {
                    try {
                        ioEngine.startServer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        shutdownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Start start socket server
                if (ioEngine != null) {
                    try {
                        ioEngine.shutdownServer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        closeGuiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ioEngine.shutdownServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }


}
