package org.teilen_webcam.server.gui;

import org.teilen_webcam.common.domain.Room;
import org.teilen_webcam.common.domain.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ActivityPanel extends JSplitPane {
    private final JList<String> userList;
    private final List<User> users;
    private final DefaultListModel<String> userModelList;
    private final JScrollPane userScrollPane;
    private final ImageIcon userImageIcon = new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-user-30.png"));


    public ActivityPanel() {
        this.setContinuousLayout(true);
        this.setOrientation(SwingConstants.VERTICAL);

        this.users = new ArrayList<>();
        this.userModelList = new DefaultListModel<>();
        this.userList = new JList<>(userModelList);
        this.userList.setCellRenderer(new UserIconRenderer());
        this.userScrollPane = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        this.setLeftComponent(userScrollPane);
    }

    public void addRoom(Room room) {
    }

    public void addUser(User user) {
        this.users.add(user);
        String username = null;
        if (user != null) {
            username = user.getFirstname() + " " + user.getLastname();
        } else
            username = "";
        this.userModelList.addElement(username);
    }


    class UserIconRenderer extends DefaultListCellRenderer {
        Font font = new Font("helvitica", Font.BOLD, 13);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String username = label.getText();
            username = "" + username + " ";
            label.setText(username);
            label.setIcon(userImageIcon);
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }


}

