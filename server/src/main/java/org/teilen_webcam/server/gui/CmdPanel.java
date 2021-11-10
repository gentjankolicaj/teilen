package org.teilen_webcam.server.gui;

import org.teilen_webcam.server.util.LogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CmdPanel extends JPanel {
    private final JButton closeGuiBtn;
    private final JButton shutdownBtn;
    private final JButton startBtn;
    private final List<Runnable> engines;
    private final ExecutorService executor;

    public CmdPanel(List<Runnable> engines, ExecutorService executor) {
        this.engines = engines;
        this.executor = executor;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.startBtn = new JButton("Start");
        this.shutdownBtn = new JButton("Shutdown");
        this.closeGuiBtn = new JButton("Close gui");

        this.add(startBtn);
        this.add(shutdownBtn);
        this.add(closeGuiBtn);

        this.addBtnListeners();
    }


    public void addBtnListeners() {
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Start server threads and that leads to starting socket server
                if (executor.isShutdown()) {
                    LogUtil.info("Executor is shutdown and submit(runnable) about to be invoked.");
                    for (Runnable engine : engines)
                        executor.submit(engine);
                } else {
                    LogUtil.warn("Executor is not shutdown !!! No submit(runnable) invoked.");
                }
            }
        });

        shutdownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Shutdown socket server
                if (!executor.isShutdown()) {
                    executor.shutdownNow();
                    LogUtil.info("Executor was not shutdown and shutdownNow() invoked.");
                } else {
                    LogUtil.info("Executor was shutdown and shutdownNow() not invoked.");
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
