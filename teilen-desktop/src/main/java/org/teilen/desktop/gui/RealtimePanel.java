package org.teilen.desktop.gui;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.teilen.desktop.domain.ConnState;
import org.teilen.desktop.repository.ConnRepository;

public class RealtimePanel extends JPanel {

  //parent panel
  final ActivityPanel activityPanel;

  final JScrollPane userScrollPane;
  final UserPanel userPanel;
  final ListSelectionModel userSelectionModel;
  boolean roomOpen;
  //server image
  private JLabel serverImage;

  public RealtimePanel(ActivityPanel activityPanel) {
    this.activityPanel = activityPanel;
    this.setLayout(new BorderLayout(0, 0));
    this.setServerImageRed();

    this.userPanel = new UserPanel();
    this.userSelectionModel = userPanel.getSelectionModel();
    this.userSelectionModel.addListSelectionListener(new SingleListSelectionHandler());

    this.userScrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.add(userScrollPane, BorderLayout.CENTER);

  }

  private static ImageIcon getServerRedImageIcon() {
    return new ImageIcon(
        ActivityPanel.class.getClassLoader().getResource("icons8-server-red-40.png"));
  }

  private static ImageIcon getServerGreenImageIcon() {
    return new ImageIcon(
        ActivityPanel.class.getClassLoader().getResource("icons8-server-green-40.png"));
  }

  public void setServerImageRed() {
    if (serverImage != null) {
      this.remove(serverImage);
    }
    this.serverImage = new JLabel("~ offline ");
    this.serverImage.setIcon(getServerRedImageIcon());
    this.add(serverImage, BorderLayout.NORTH);
    this.validate();
  }

  public void setServerImageGreen() {
    if (serverImage != null) {
      this.remove(serverImage);
    }
    this.serverImage = new JLabel("~ online ");
    this.serverImage.setIcon(getServerGreenImageIcon());
    this.add(serverImage, BorderLayout.NORTH);
    this.validate();
  }


  public void validateGui() {
    ConnState connState = ConnRepository.findConnState();
    if (connState != null) {
      if (connState.name().equals(ConnState.ONLINE.name())) {
        setServerImageGreen();
      } else if (connState.name().equals(ConnState.OFFLINE.name())) {
        setServerImageRed();
      }
      userPanel.updateClients();
      userScrollPane.validate();
    }
  }

  public void setRoomOpen(boolean flag) {
    this.roomOpen = flag;
  }


  class SingleListSelectionHandler implements ListSelectionListener {

    Integer initClientId = null;

    public void valueChanged(ListSelectionEvent e) {
      ListSelectionModel lsm = (ListSelectionModel) e.getSource();
      // Find out which indexes are selected.
      int minIndex = lsm.getMinSelectionIndex();
      int maxIndex = lsm.getMaxSelectionIndex();
      int selectedIndex = minIndex;
      for (int i = minIndex; i <= maxIndex; i++) {
        if (lsm.isSelectedIndex(i)) {
          selectedIndex = i;
          break;
        }
      }
      Integer clientId = userPanel.getClientId(selectedIndex);
      if (clientId != null) {
        if (roomOpen) {
          if (clientId.intValue() != initClientId.intValue()) {
            initClientId = clientId;
            activityPanel.openRoomWithClient(clientId);
          }
        } else {
          initClientId = clientId;
          activityPanel.openRoomWithClient(clientId);
          roomOpen = true;
        }
      }
    }
  }

}

