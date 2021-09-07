package org.teilen_webcam.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final JPanel cmdPanel;
    private final JTextField hostTF;
    private final JTextField portTF;
    private final JTextField timeoutTF;

    public InfoPanel() {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setLayout(new BorderLayout(0, 0));

        cmdPanel = new JPanel();
        add(cmdPanel, BorderLayout.NORTH);
        cmdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel hostLbl = new JLabel("Host : ");
        cmdPanel.add(hostLbl);

        hostTF = new JTextField();
        hostTF.setText("http://localhost");
        cmdPanel.add(hostTF);
        hostTF.setColumns(10);

        Component horizontalStrut_6 = Box.createHorizontalStrut(10);
        cmdPanel.add(horizontalStrut_6);

        JLabel portLbl = new JLabel("Port :");
        cmdPanel.add(portLbl);

        portTF = new JTextField();
        portTF.setText("8899");
        cmdPanel.add(portTF);
        portTF.setColumns(10);

        Component horizontalStrut_7 = Box.createHorizontalStrut(10);
        cmdPanel.add(horizontalStrut_7);

        JLabel timeoutLbl = new JLabel("Timeout(mil) : ");
        cmdPanel.add(timeoutLbl);

        timeoutTF = new JTextField();
        timeoutTF.setText("1000");
        cmdPanel.add(timeoutTF);
        timeoutTF.setColumns(10);

        Component horizontalStrut_8 = Box.createHorizontalStrut(10);
        cmdPanel.add(horizontalStrut_8);

        JLabel statusLbl = new JLabel("Status ");
        cmdPanel.add(statusLbl);

        JLabel iconStatusLbl = new JLabel("");
        cmdPanel.add(iconStatusLbl);


        JButton connectBtn = new JButton("Connect");
        cmdPanel.add(connectBtn);

        JButton disconnectBtn = new JButton("Disconnect");
        cmdPanel.add(disconnectBtn);

        JPanel softwareInfoPanel = new JPanel();
        softwareInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        add(softwareInfoPanel, BorderLayout.CENTER);
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

        JLabel userLbl = new JLabel("User :");
        softwareInfoPanel.add(userLbl);

        JLabel userLblValue = new JLabel("johndoe");
        softwareInfoPanel.add(userLblValue);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        softwareInfoPanel.add(horizontalStrut_1);

        JLabel userDirLbl = new JLabel("User dir :");
        softwareInfoPanel.add(userDirLbl);

        JLabel userDirLblValue = new JLabel("/home/user");
        softwareInfoPanel.add(userDirLblValue);

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
