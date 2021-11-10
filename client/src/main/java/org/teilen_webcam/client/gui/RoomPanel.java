package org.teilen_webcam.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RoomPanel extends JPanel {
    HeaderPanel headerPanel;
    BodyPanel bodyPanel;
    FooterPanel footerPanel;
    BorderLayout borderLayout;


    public RoomPanel() {
        this.borderLayout = new BorderLayout(0, 0);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setLayout(borderLayout);

        this.headerPanel = new HeaderPanel("John Doe");
        this.bodyPanel = new BodyPanel();
        this.footerPanel = new FooterPanel();

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

    }

    //To be called when on room or client is clicked
    public void openRoomPanel() {
        this.headerPanel = new HeaderPanel("John Doe");
        this.bodyPanel = new BodyPanel();
        this.footerPanel = new FooterPanel();

        setComponentLayout();
    }

    private void setComponentLayout() {
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }


}

class HeaderPanel extends JPanel {
    JPanel westPanel;
    JPanel eastPanel;

    String username;

    JLabel usernameLbl;
    JLabel addUserLbl;
    JLabel cameraLbl;
    JLabel phoneLbl;
    Map<String, ImageIcon> iconMap = getIconMap();


    public HeaderPanel(String username) {
        this.username = username;
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new BorderLayout(0, 0));


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
        map.put("User", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-user-30.png")));
        map.put("AddUser", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-add-user-30.png")));
        map.put("Camera", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-video-camera-30.png")));
        map.put("Phone", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-phone-30.png")));
        return map;
    }


}

class BodyPanel extends JPanel {
    JScrollPane jScrollPane;

    public BodyPanel() {

    }


    class BodyDay extends JPanel {

    }
}

class FooterPanel extends JPanel {
    JPanel centerPanel;
    JPanel eastPanel;

    JLabel addFileLbl;
    JLabel recordLbl;
    JTextField messageTF;
    Map<String, ImageIcon> iconMap = getIconMap();


    public FooterPanel() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new BorderLayout(0, 0));

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
        map.put("AddFile", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-add-file-20.png")));
        map.put("Record", new ImageIcon(UserPanel.class.getClassLoader().getResource("icons8-add-record-20.png")));
        return map;
    }

}
