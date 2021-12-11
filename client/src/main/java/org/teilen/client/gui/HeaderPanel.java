package org.teilen.client.gui;

import org.teilen.client.repository.ClientRepository;
import org.teilen.common.domain.Client;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HeaderPanel extends JPanel {
    Integer clientId;

    JPanel westPanel;
    JPanel eastPanel;

    JLabel usernameLbl;
    JLabel addUserLbl;
    JLabel cameraLbl;
    JLabel phoneLbl;
    Map<String, ImageIcon> iconMap = getIconMap();


    public HeaderPanel(Integer clientId) {
        this.clientId = clientId;
        this.setLayout(new BorderLayout());

        String username = getUsername(ClientRepository.findClientById(clientId));

        this.westPanel = new JPanel();
        this.eastPanel = new JPanel();

        this.usernameLbl = new JLabel(username);
        this.addUserLbl = new JLabel("  ");
        this.cameraLbl = new JLabel("  ");
        this.phoneLbl = new JLabel("  ");

        this.usernameLbl.setIcon(iconMap.get("User"));
        this.addUserLbl.setIcon(iconMap.get("AddUser"));
        this.cameraLbl.setIcon(iconMap.get("Camera"));
        this.phoneLbl.setIcon(iconMap.get("Phone"));

        //Add usernameLbl to westPanel
        this.westPanel.add(usernameLbl);

        //Other Lbl to east panel
        this.eastPanel.add(addUserLbl);
        this.eastPanel.add(cameraLbl);
        this.eastPanel.add(phoneLbl);

        //Set panels east & west
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);

        //Add contentPane to parent panel
    }


    private Map<String, ImageIcon> getIconMap() {
        Map<String, ImageIcon> map = new HashMap<>();
        map.put("User", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-user-30.png")));
        map.put("AddUser", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-user-30.png")));
        map.put("Camera", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-video-camera-30.png")));
        map.put("Phone", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-phone-30.png")));
        return map;
    }

    private String getUsername(Client client) {
        String username = null;
        if (client != null) {
            username = client.getFirstname() + " " + client.getLastname() + " : " + client.getId();
        } else
            username = "";
        return username;
    }


}