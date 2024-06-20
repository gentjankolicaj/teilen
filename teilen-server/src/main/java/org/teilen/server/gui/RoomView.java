package org.teilen.server.gui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomView extends JPanel {

  private final Integer roomId;
  private final Integer members;
  private final BoxLayout boxLayout;
  private final JLabel roomIconLbl;
  private final JLabel roomIdLbl;
  private String name;
  private JLabel roomMembersLbl;


  public RoomView(Integer roomId, Integer members) {
    this.roomId = roomId;
    this.members = members;

    this.roomIconLbl = new JLabel();
    this.roomIconLbl.setIcon(getRoomIcon());
    this.roomIdLbl = new JLabel(name + "-ID:" + this.roomId);
    this.roomMembersLbl = new JLabel("U:" + this.members);

    this.add(roomIconLbl);
    this.add(roomIdLbl);
    this.add(roomMembersLbl);

    this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
  }

  public RoomView(Integer roomId, Integer members, String name) {
    this.roomId = roomId;
    this.members = members;
    this.name = name;

    this.roomIconLbl = new JLabel();
    this.roomIconLbl.setIcon(getRoomIcon());
    this.roomIdLbl = new JLabel(name + "-ID:" + this.roomId);
    this.roomMembersLbl = new JLabel("U:" + this.members);

    this.add(roomIconLbl);
    this.add(roomIdLbl);
    this.add(roomMembersLbl);

    this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
  }


  private static ImageIcon getRoomIcon() {
    return new ImageIcon(RoomView.class.getClassLoader().getResource("icons8-group-30.png"));
  }

  public void updateMembers(Integer members) {
    if (this.members != members) {
      this.remove(roomMembersLbl);
      this.roomMembersLbl = new JLabel("U:" + this.members);
    }
  }

}
