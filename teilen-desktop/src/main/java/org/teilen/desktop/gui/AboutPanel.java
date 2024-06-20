package org.teilen.desktop.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JPanel {

  private final JLabel developer;
  private final JLabel website;
  private final JLabel other;

  public AboutPanel() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.developer = new JLabel("Developer : Gentjan Kolicaj");
    this.website = new JLabel("Website : https://github.com/gentjankolicaj");
    this.other = new JLabel("Icons : https://icons8.com/icons");

    this.add(developer);
    this.add(website);
    this.add(other);
  }
}
