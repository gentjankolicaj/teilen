package org.teilen_webcam.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UserPanel extends JPanel {
    private final JList<String> userList;
    private final JScrollPane jScrollPane;
    private final String[] nameList;
    private final Map<String, ImageIcon> userMap;


    public UserPanel() {
        this.setLayout(new BorderLayout());
        this.nameList = getNameList();
        this.userMap = getImageMap();
        this.userList = new JList<>(nameList);
        userList.setCellRenderer(new ListImageRenderer());

        this.jScrollPane = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(jScrollPane, BorderLayout.CENTER);
    }


    private Map<String, ImageIcon> getImageMap() {
        Map<String, ImageIcon> map = new HashMap<>();
        map.put("Server", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-server-40.png")));
        map.put("John Doe", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-user-40.png")));
        map.put("Room", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-chat-40.png")));

        return map;
    }

    public String[] getNameList() {
        String[] array = new String[100];
        for (int i = 0; i < 100; i++) {
            if (i == 0) {
                array[i] = "Server";
            } else if (i % 2 == 0) {
                array[i] = "John Doe";
            } else {
                array[i] = "Room";
            }
        }
        return array;
    }

    class ListImageRenderer extends DefaultListCellRenderer {
        Font font = new Font("helvitica", Font.BOLD, 13);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String username = label.getText();
            username = "" + username + "     ";
            label.setText(username);
            label.setIcon(userMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }


}
