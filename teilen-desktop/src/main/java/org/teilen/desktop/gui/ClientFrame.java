package org.teilen.desktop.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.teilen.desktop.engine.ActivityEngine;
import org.teilen.desktop.engine.IOEngine;
import org.teilen.desktop.global.GlobalConfig;

public class ClientFrame extends JFrame {

  static ClientFrame mainFrame;

  private final JPanel contentPane;
  private final InfoPanel infoPanel;
  private final ActivityPanel activityPanel;
  private final AboutPanel aboutPanel;


  /**
   * @param ioEngine
   * @param activityEngine
   */
  public ClientFrame(IOEngine ioEngine, ActivityEngine activityEngine) {
    this.setTitle("Teilen ~ desktop");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(GlobalConfig.xBound, GlobalConfig.yBound, GlobalConfig.clientWidth,
        GlobalConfig.clientHeight);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    this.setContentPane(contentPane);

    this.infoPanel = new InfoPanel(ioEngine);
    contentPane.add(infoPanel, BorderLayout.NORTH);

    this.activityPanel = new ActivityPanel();
    contentPane.add(activityPanel, BorderLayout.CENTER);

    this.aboutPanel = new AboutPanel();
    contentPane.add(aboutPanel, BorderLayout.SOUTH);

    this.setVisible(true);
    this.setResizable(false);
    this.pack();

    //Set panels to be used by activity engine
    activityEngine.setActivityPanel(activityPanel);
    activityEngine.setInfoPanel(infoPanel);
    mainFrame = this;
  }

}
