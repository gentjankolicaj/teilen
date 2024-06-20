package org.teilen.server.gui;


import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.teilen.server.global.GlobalConfig;


public class ActivityPanel extends JSplitPane {

  private final JScrollPane userScrollPane;
  private final UserPanel userPanel;

  private final JScrollPane roomScrollPane;
  private final RoomPanel roomPanel;

  public ActivityPanel() {
    this.setPreferredSize(
        new Dimension((int) (GlobalConfig.width * 0.75), (int) (GlobalConfig.height * 0.5)));

    this.userPanel = new UserPanel();
    this.userScrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.roomPanel = new RoomPanel();
    this.roomScrollPane = new JScrollPane(roomPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.setLeftComponent(userScrollPane);
    this.setRightComponent(roomScrollPane);
  }


  public void validateGui() {
    validateClientGui();
    validateRoomGui();
  }

  private void validateRoomGui() {
    roomPanel.updateRooms();
  }

  private void validateClientGui() {
    userPanel.updateClients();
  }


}

