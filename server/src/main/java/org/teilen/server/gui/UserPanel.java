package org.teilen.server.gui;

import org.teilen.common.domain.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserPanel extends JList<String> {
    static DefaultListModel<String> userModelList = new DefaultListModel<>();
    static List<User> users = new ArrayList<>();
    private final ImageIcon userImageIcon = getUserImageIcon();

    public UserPanel() {
        super(userModelList);
        this.setCellRenderer(new UserIconRenderer());
    }

    public static ImageIcon getUserImageIcon() {
        return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-user-30.png"));
    }

    public void addUser(User user) {
        if (users != null) {
            boolean found = false;
            for (int i = 0; i < users.size(); i++) {
                User tmp = users.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = user.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                int addIndex = users.size(); //because is total number and index start from 0
                users.add(user);
                String username = getUsername(user);
                userModelList.add(addIndex, username);

                /** todo in future
                 userModelList.add(addIndex,new UserModel(user.getId(),username));
                 */
            }
        }

    }

    private String getUsername(User user) {
        String username = null;
        if (user != null) {
            username = user.getFirstname() + " " + user.getLastname() + ":" + user.getId();
        } else
            username = "";
        return username;
    }

    public void removeUser(User user) {
        if (users != null && users.size() != 0) {
            boolean found = false;
            int removeIndex = -1;
            for (int i = 0; i < users.size(); i++) {
                User tmp = users.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = user.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    found = true;
                    removeIndex = i;
                    break;
                }
            }
            if (found) {
                users.remove(removeIndex);
                userModelList.remove(removeIndex);
            }
        }
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

class UserModel {
    Integer userId;
    String username;

    public UserModel(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
