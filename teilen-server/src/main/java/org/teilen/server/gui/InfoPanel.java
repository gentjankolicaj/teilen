package org.teilen.server.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {


  public InfoPanel() {
    setLayout(new BorderLayout(0, 0));

    JPanel softwareInfoPanel = new JPanel();
    add(softwareInfoPanel, BorderLayout.NORTH);
    softwareInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JLabel hostOsLbl = new JLabel("OS :");
    softwareInfoPanel.add(hostOsLbl);

    JLabel hostOsLblValue = new JLabel("Ubuntu");
    softwareInfoPanel.add(hostOsLblValue);

    Component horizontalStrut = Box.createHorizontalStrut(20);
    softwareInfoPanel.add(horizontalStrut);

    JLabel jreLbl = new JLabel("JRE :");
    softwareInfoPanel.add(jreLbl);

    JLabel jreLblValue = new JLabel("1.8.0_281-b09");
    softwareInfoPanel.add(jreLblValue);

    Component horizontalStrut_5 = Box.createHorizontalStrut(20);
    softwareInfoPanel.add(horizontalStrut_5);

    JLabel hostLbl = new JLabel("Host :");
    softwareInfoPanel.add(hostLbl);

    JLabel hostLblValue = new JLabel("http://localhost");
    softwareInfoPanel.add(hostLblValue);

    Component horizontalStrut_1 = Box.createHorizontalStrut(20);
    softwareInfoPanel.add(horizontalStrut_1);

    JLabel hostPortLbl = new JLabel("Port :");
    softwareInfoPanel.add(hostPortLbl);

    JLabel hostPortLblValue = new JLabel("8888");
    softwareInfoPanel.add(hostPortLblValue);

    JPanel hardwareInfoPanel = new JPanel();
    add(hardwareInfoPanel, BorderLayout.SOUTH);
    hardwareInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JLabel cpuLbl = new JLabel("Cpu :");
    hardwareInfoPanel.add(cpuLbl);

    JLabel cpuLblValue = new JLabel("i5");
    hardwareInfoPanel.add(cpuLblValue);

    Component horizontalStrut_2 = Box.createHorizontalStrut(20);
    hardwareInfoPanel.add(horizontalStrut_2);

    JLabel cpuLoadLbl = new JLabel("Cpu-load : ");
    hardwareInfoPanel.add(cpuLoadLbl);

    JLabel cpuLoadLblValue = new JLabel("5%");
    hardwareInfoPanel.add(cpuLoadLblValue);

    Component horizontalStrut_3 = Box.createHorizontalStrut(20);
    hardwareInfoPanel.add(horizontalStrut_3);

    JLabel totalMemLbl = new JLabel("Total mem :");
    hardwareInfoPanel.add(totalMemLbl);

    JLabel totalMemLblValue = new JLabel("20 kb");
    hardwareInfoPanel.add(totalMemLblValue);

    Component horizontalStrut_4 = Box.createHorizontalStrut(20);
    hardwareInfoPanel.add(horizontalStrut_4);

    JLabel freeMemLbl = new JLabel("Free mem :");
    hardwareInfoPanel.add(freeMemLbl);

    JLabel freeMemLblValue = new JLabel("30kb");
    hardwareInfoPanel.add(freeMemLblValue);

  }


}
