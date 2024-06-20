package org.teilen.server.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.teilen.server.engine.ActivityEngine;
import org.teilen.server.engine.IOEngine;
import org.teilen.server.global.GlobalConfig;

public class ServerFrame extends JFrame {

  private final InfoPanel infoPanel;
  private final ActivityPanel activityPanel;
  private final JPanel cmdPanel;


  public ServerFrame(ActivityEngine activityEngine, IOEngine ioEngine) {
    this.setTitle("Teilen ~ SERVER");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(GlobalConfig.xBound, GlobalConfig.yBound, GlobalConfig.width,
        GlobalConfig.height);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));

    this.infoPanel = new InfoPanel();
    this.activityPanel = new ActivityPanel();
    this.cmdPanel = new CmdPanel(ioEngine);

    contentPane.add(infoPanel, BorderLayout.NORTH);
    contentPane.add(activityPanel, BorderLayout.CENTER);
    contentPane.add(cmdPanel, BorderLayout.SOUTH);

    this.setVisible(true);
    this.setResizable(true);
    this.pack();

    //Set panels to be used by activity engine
    activityEngine.setActivityPanel(activityPanel);
  }
}
