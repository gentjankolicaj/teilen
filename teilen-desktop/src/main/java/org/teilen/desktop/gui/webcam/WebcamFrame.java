package org.teilen.desktop.gui.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.teilen.desktop.global.GlobalConfig;

public class WebcamFrame extends JFrame {

  final JPanel contentPane;
  final ImagePanel ownerComponent;
  final ImagePanel guestComponent;
  Webcam webcam;


  public WebcamFrame() throws HeadlessException {
    this.setTitle("Teilen_webcam");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setBounds(GlobalConfig.xBound, GlobalConfig.yBound, 660, 310);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout());
    this.setContentPane(contentPane);

    int width = getWidth();
    int height = getHeight();
    this.ownerComponent = new ImagePanel(width, height);
    this.guestComponent = new ImagePanel(width, height);

    this.contentPane.add(ownerComponent, BorderLayout.EAST);
    this.contentPane.add(guestComponent, BorderLayout.WEST);

    this.webcam = Webcam.getDefault();

    this.setVisible(true);
    this.setResizable(false);

    this.addWindowListener(new WebcamFrameListener());
  }

  public void validateGui() {
    if (webcam != null && webcam.isOpen()) {
      BufferedImage bufferedImage = webcam.getImage();
      ownerComponent.updateImage(bufferedImage);
      guestComponent.updateImage(bufferedImage);

    }
  }


  class WebcamFrameListener implements WindowListener {

    @Override
    public void windowOpened(WindowEvent windowEvent) {
      if (webcam != null) {
        webcam.setViewSize(WebcamResolution.QVGA.getSize());
        webcam.open();
      } else {
        webcam = Webcam.getDefault();
      }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
      if (webcam != null) {
        webcam.close();
      }
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
  }


}


