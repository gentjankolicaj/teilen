package org.teilen.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import org.teilen.desktop.domain.ConnState;
import org.teilen.desktop.global.GlobalConfig;
import org.teilen.desktop.repository.ConnRepository;
import org.teilen.desktop.util.LogUtil;


public class ActivityPanel extends JPanel {

  private final RealtimePanel realtimePanel;
  private RoomPanel roomPanel;
  private long lastRefreshTime;


  public ActivityPanel() {
    this.setPreferredSize(
        new Dimension(GlobalConfig.clientWidth, (int) (GlobalConfig.clientHeight * 0.55)));
    this.setBorder(new LineBorder(new Color(0, 0, 0)));
    this.setLayout(new BorderLayout(0, 0));
    this.realtimePanel = new RealtimePanel(this);
    this.add(realtimePanel, BorderLayout.WEST);
    this.roomPanel = new RoomPanel();
    this.add(roomPanel, BorderLayout.CENTER);
  }


  public void openRoomWithClient(Integer clientId) {
    this.remove(roomPanel);
    RoomPanel roomPanel = new RoomPanel(clientId);
    this.roomPanel = roomPanel;
    this.add(roomPanel, BorderLayout.CENTER);
    this.validate();
  }

  private void clearRoom() {
    this.remove(roomPanel);
    this.realtimePanel.setRoomOpen(false);
    RoomPanel roomPanel = new RoomPanel();
    this.roomPanel = roomPanel;
    this.add(roomPanel, BorderLayout.CENTER);
    this.validate();
  }


  public void validateGui() {
    long currentTime = System.currentTimeMillis();
    long diff = currentTime - lastRefreshTime;
    if (diff > GlobalConfig.APRefreshThreshold) {
      this.realtimePanel.validateGui();
      if (ConnRepository.findConnState().name().equals(ConnState.OFFLINE.name())) {
        this.clearRoom();
      } else {
        if (this.roomPanel != null) {
          this.roomPanel.validateGui();
        }
      }
      LogUtil.info("AP refresh time : " + currentTime + " last refresh time : " + lastRefreshTime);
      lastRefreshTime = System.currentTimeMillis();
    }
  }


}
