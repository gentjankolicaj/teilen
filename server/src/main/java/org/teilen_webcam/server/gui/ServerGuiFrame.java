package org.teilen_webcam.server.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ServerGuiFrame extends JFrame {

    private final JPanel contentPane;
    private final InfoPanel infoPanel;
    private final ActivityPanel activityPanel;
    private final JPanel cmdPanel;


    /**
     * Create the frame.
     */
    public ServerGuiFrame(List<Runnable> engines) {
        this.setTitle("Teilen-server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 900, 750);

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        this.infoPanel = new InfoPanel();
        this.activityPanel = new ActivityPanel();
        this.cmdPanel = new CmdPanel(engines);

        this.contentPane.add(infoPanel, BorderLayout.NORTH);
        this.contentPane.add(activityPanel, BorderLayout.CENTER);
        this.contentPane.add(cmdPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }


}
