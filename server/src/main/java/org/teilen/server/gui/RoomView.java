package org.teilen.server.gui;

import javax.swing.*;

public class RoomView extends JPanel {
    private final Integer roomId;
    private final Integer members;
    private final BoxLayout boxLayout;
    private final JLabel roomIconLbl;
    private final JLabel roomIdLbl;
    private String name;
    private JLabel roomMembersLbl;


    public RoomView(Integer roomId) {
        this.roomId = roomId;
        this.members = 0;

        this.roomIconLbl = new JLabel();
        this.roomIconLbl.setIcon(getRoomIcon());
        this.roomIdLbl = new JLabel("ID:" + this.roomId);
        this.roomMembersLbl = new JLabel("U:" + this.members);


        this.add(roomIconLbl);
        this.add(roomIdLbl);
        this.add(roomMembersLbl);

        this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    }

    public RoomView(Integer roomId, String name) {
        this.roomId = roomId;
        this.members = 0;
        this.name = name;

        this.roomIconLbl = new JLabel();
        this.roomIconLbl.setIcon(getRoomIcon());
        this.roomIdLbl = new JLabel("ID:" + this.roomId);
        this.roomMembersLbl = new JLabel("U:" + this.members);


        this.add(roomIconLbl);
        this.add(roomIdLbl);
        this.add(roomMembersLbl);

        this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    }

    public RoomView(Integer roomId, Integer members) {
        this.boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.roomId = roomId;
        this.members = members;

        this.roomIconLbl = new JLabel();
        this.roomIconLbl.setIcon(getRoomIcon());
        this.roomIdLbl = new JLabel("ID:" + this.roomId);
        this.roomMembersLbl = new JLabel("U:" + this.members);


        this.add(roomIconLbl);
        this.add(roomIdLbl);
        this.add(roomMembersLbl);
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
