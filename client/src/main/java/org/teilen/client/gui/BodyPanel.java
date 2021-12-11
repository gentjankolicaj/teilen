package org.teilen.client.gui;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {
    Integer clientId;

    JScrollPane jScrollPane;

    public BodyPanel(Integer clientId) {
        this.clientId = clientId;
        this.setLayout(new BorderLayout(0, 0));
    }


    class BodyDay extends JPanel {

    }
}
