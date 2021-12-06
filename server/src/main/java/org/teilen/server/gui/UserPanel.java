package org.teilen.server.gui;

import org.teilen.common.domain.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserPanel extends JList<String> {
    static DefaultListModel<String> userModelList = new DefaultListModel<>();
    static List<Client> clients = new ArrayList<>();
    private final ImageIcon userImageIcon = getUserImageIcon();

    public UserPanel() {
        super(userModelList);
        this.setCellRenderer(new UserIconRenderer());
    }

    public static ImageIcon getUserImageIcon() {
        return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-user-30.png"));
    }

    public void addUser(Client client) {
        if (clients != null) {
            boolean found = false;
            for (int i = 0; i < clients.size(); i++) {
                Client tmp = clients.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = client.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                int addIndex = clients.size(); //because is total number and index start from 0
                clients.add(client);
                String username = getUsername(client);
                userModelList.add(addIndex, username);

                /** todo in future
                 userModelList.add(addIndex,new UserModel(user.getId(),username));
                 */
            }
        }

    }

    private String getUsername(Client client) {
        String username = null;
        if (client != null) {
            username = client.getFirstname() + " " + client.getLastname() + ":" + client.getId();
        } else
            username = "";
        return username;
    }

    public void removeUser(Client client) {
        if (clients != null && clients.size() != 0) {
            boolean found = false;
            int removeIndex = -1;
            for (int i = 0; i < clients.size(); i++) {
                Client tmp = clients.get(i);
                Integer tmpUserId = tmp.getId();
                Integer userId = client.getId();
                if ((tmpUserId != null && userId != null) && (tmpUserId.intValue() == userId.intValue())) {
                    found = true;
                    removeIndex = i;
                    break;
                }
            }
            if (found) {
                clients.remove(removeIndex);
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
