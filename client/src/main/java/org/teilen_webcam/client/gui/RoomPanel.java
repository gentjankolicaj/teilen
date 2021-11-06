package org.teilen_webcam.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RoomPanel extends JPanel {
    HeaderPanel headerPanel;
    BodyPanel bodyPanel;
    FooterPanel footerPanel;


    public RoomPanel() {
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setLayout(new BorderLayout(0, 0));

        this.headerPanel = new HeaderPanel();
        this.bodyPanel = new BodyPanel();
        this.footerPanel = new FooterPanel();

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

    }


}

class HeaderPanel extends JPanel {

}

class BodyPanel extends JPanel {

}

class FooterPanel extends JPanel {

}
