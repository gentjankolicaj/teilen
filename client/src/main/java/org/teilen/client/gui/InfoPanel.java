package org.teilen.client.gui;

import org.apache.commons.lang3.math.NumberUtils;
import org.teilen.client.domain.SocketMeta;
import org.teilen.client.engine.IOEngine;
import org.teilen.client.util.LogUtil;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InfoPanel extends JPanel {
    private final JPanel buttonPanel;
    private final JPanel softwareInfoPanel;
    private final JPanel userInfoPanel;

    private final JTextField hostTF;
    private final JTextField portTF;
    private final JTextField timeoutTF;

    private final JTextField usernameTF;
    private final JTextField dirTF;
    private final JLabel userId;

    private final JButton connectBtn;
    private final JButton disconnectBtn;
    private final IOEngine ioEngine;

    public InfoPanel(IOEngine ioEngine) {
        this.ioEngine = ioEngine;
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setLayout(new BorderLayout(0, 0));

        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        JLabel hostLbl = new JLabel("Host : ");
        buttonPanel.add(hostLbl);

        hostTF = new JTextField();
        hostTF.setText("localhost");
        buttonPanel.add(hostTF);
        hostTF.setColumns(13);

        Component horizontalStrut_1 = Box.createHorizontalStrut(10);
        buttonPanel.add(horizontalStrut_1);

        JLabel portLbl = new JLabel("Port :");
        buttonPanel.add(portLbl);

        portTF = new JTextField();
        portTF.setText("8888");
        buttonPanel.add(portTF);
        portTF.setColumns(10);
        buttonPanel.add(horizontalStrut_1);

        JLabel timeoutLbl = new JLabel("Timeout(millis) : ");
        buttonPanel.add(timeoutLbl);

        timeoutTF = new JTextField();
        timeoutTF.setText("1000");
        buttonPanel.add(timeoutTF);
        timeoutTF.setColumns(10);

        buttonPanel.add(horizontalStrut_1);

        JLabel iconStatusLbl = new JLabel("");
        buttonPanel.add(iconStatusLbl);

        connectBtn = new JButton("Connect");
        buttonPanel.add(connectBtn);

        disconnectBtn = new JButton("Disconnect");
        buttonPanel.add(disconnectBtn);


        // User info panel  setup */
        userInfoPanel = new JPanel();
        add(userInfoPanel, BorderLayout.CENTER);
        userInfoPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        JLabel userIdLbl = new JLabel("ID :");
        userInfoPanel.add(userIdLbl);

        userId = new JLabel("       ");
        userInfoPanel.add(userId);

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        userInfoPanel.add(horizontalStrut_2);

        JLabel userLbl = new JLabel("  Uname :");
        userInfoPanel.add(userLbl);

        usernameTF = new JTextField();
        usernameTF.setText("john doe");
        usernameTF.setColumns(13);
        userInfoPanel.add(usernameTF);
        userInfoPanel.add(horizontalStrut_2);

        JLabel userDirLbl = new JLabel("  Dir :");
        userInfoPanel.add(userDirLbl);

        dirTF = new JTextField();
        dirTF.setText("/home/");
        userInfoPanel.add(dirTF);
        dirTF.setColumns(13);
        userInfoPanel.add(horizontalStrut_2);


        //Software & hardware info panel
        softwareInfoPanel = new JPanel();
        add(softwareInfoPanel, BorderLayout.SOUTH);
        softwareInfoPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

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

        softwareInfoPanel.add(horizontalStrut);

        JLabel cpuLbl = new JLabel("Cpu :");
        softwareInfoPanel.add(cpuLbl);

        JLabel cpuLblValue = new JLabel("i5");
        softwareInfoPanel.add(cpuLblValue);

        softwareInfoPanel.add(horizontalStrut);

        JLabel cpuLoadLbl = new JLabel("Cpu-load : ");
        softwareInfoPanel.add(cpuLoadLbl);

        JLabel cpuLoadLblValue = new JLabel("5%");
        softwareInfoPanel.add(cpuLoadLblValue);

        Component horizontalStrut_4 = Box.createHorizontalStrut(20);
        softwareInfoPanel.add(horizontalStrut_4);

        JLabel totalMemLbl = new JLabel("Total mem :");
        softwareInfoPanel.add(totalMemLbl);

        JLabel totalMemLblValue = new JLabel("20 kb");
        softwareInfoPanel.add(totalMemLblValue);

        softwareInfoPanel.add(horizontalStrut);

        JLabel freeMemLbl = new JLabel("Free mem :");
        softwareInfoPanel.add(freeMemLbl);

        JLabel freeMemLblValue = new JLabel("30kb");
        softwareInfoPanel.add(freeMemLblValue);


        //Add button listeners
        addBtnActionListeners();
    }

    public void addBtnActionListeners() {
        //Perform action on disconnect
        this.connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int timeout = getTimeout();
                int port = getPort();
                String host = getHost();
                String username = getUsername();

                LogUtil.info("Connect button pressed.");
                try {
                    ioEngine.connect(new SocketMeta(host, port, timeout, username));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Perform action on disconnect
        this.disconnectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LogUtil.info("Disconnect button pressed.");
                try {
                    ioEngine.disconnect();
                    removeExistingUserId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private int getTimeout() {
        String timeoutStr = this.timeoutTF.getText();
        return NumberUtils.toInt(timeoutStr);
    }

    private int getPort() {
        String timeoutStr = this.portTF.getText();
        return NumberUtils.toInt(timeoutStr);
    }

    private String getHost() {
        String hostStr = this.hostTF.getText();
        return hostStr;
    }

    private String getUsername() {
        String hostStr = this.usernameTF.getText();
        return hostStr;
    }

    private void removeExistingUserId() {
        if (userId != null) {
            userId.setText("");
        }
    }

    public void setClientId(Integer clientId) {
        if (clientId != null && userId != null) {
            userId.setText(clientId.toString());
        }
    }


    public void processGui(List<Packet> packets) {
        updateInfoMeta(packets);
    }


    private void updateInfoMeta(List<Packet> packets) {
        if (packets != null) {
            for (Packet packet : packets) {
                if (packet instanceof ClientPacket) {
                    ClientPacket clientPacket = (ClientPacket) packet;
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
                        Integer clientId = clientPacket.getClientId();
                        this.setClientId(clientId);

                    }
                }
            }
            LogUtil.info("InfoPanel packets : " + packets);
        }
    }


}
