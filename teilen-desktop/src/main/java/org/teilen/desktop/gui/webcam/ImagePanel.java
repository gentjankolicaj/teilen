package org.teilen.desktop.gui.webcam;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

  String imageName;
  JLabel imageLabel;

  public ImagePanel(int width, int height) {
    super();
    this.imageLabel = new JLabel();

    this.setLayout(new BorderLayout());
    this.add(imageLabel, BorderLayout.CENTER);
  }

  public ImagePanel(ImageIcon imageIcon, String imageName) {
    this.imageName = imageName;

    this.setLayout(new BorderLayout());
    this.add(imageLabel, BorderLayout.CENTER);
  }

  public void updateImage(ImageIcon imageIcon) {
    this.imageLabel.setIcon(imageIcon);
  }

  public void updateImage(BufferedImage bufferedImage) {
    this.imageLabel.setIcon(new ImageIcon(bufferedImage));
  }

}
