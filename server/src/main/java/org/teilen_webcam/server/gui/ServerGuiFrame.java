package org.teilen_webcam.server.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerGuiFrame extends JFrame {

    private final JPanel contentPane;
    private final InfoPanel infoPanel;
    private final JSplitPane clientSplitPane;

    private final JPanel cmdPanel;


    /**
     * Create the frame.
     */
    public ServerGuiFrame() {
        setTitle("Teilen-server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        infoPanel = new InfoPanel();
        contentPane.add(infoPanel, BorderLayout.NORTH);

        clientSplitPane = new JSplitPane();
        clientSplitPane.setContinuousLayout(true);
        contentPane.add(clientSplitPane, BorderLayout.CENTER);

        cmdPanel = new CmdPanel();
        contentPane.add(cmdPanel, BorderLayout.SOUTH);

    }

}
