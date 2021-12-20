package org.teilen.client.gui;

import org.teilen.common.domain.TextContent;

import javax.swing.*;

public class TextLabel extends JLabel {
    public TextLabel(TextContent textContent) {
        super(textContent.getText());
    }
}
