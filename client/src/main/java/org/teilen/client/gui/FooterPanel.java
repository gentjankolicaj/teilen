package org.teilen.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FooterPanel extends JPanel {
    Integer clientId;

    JPanel centerPanel;
    JPanel eastPanel;

    JLabel addFileLbl;
    JLabel recordLbl;
    JTextField messageTF;
    Map<String, ImageIcon> iconMap = getIconMap();


    public FooterPanel(Integer clientId) {
        this.clientId = clientId;
        this.setLayout(new BorderLayout());

        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BorderLayout());
        this.eastPanel = new JPanel();

        this.addFileLbl = new JLabel("  ");
        this.recordLbl = new JLabel("  ");
        this.messageTF = new JTextField();


        this.addFileLbl.setIcon(iconMap.get("AddFile"));
        this.recordLbl.setIcon(iconMap.get("Record"));

        //Add usernameLbl to westPanel
        this.centerPanel.add(messageTF, BorderLayout.CENTER);

        //Other Lbl to east panel
        this.eastPanel.add(addFileLbl);
        this.eastPanel.add(recordLbl);

        //Set panels east & west
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);

        //Add contentPane to parent panel
    }


    private Map<String, ImageIcon> getIconMap() {
        Map<String, ImageIcon> map = new HashMap<>();
        map.put("AddFile", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-file-20.png")));
        map.put("Record", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-record-20.png")));
        return map;
    }

}
