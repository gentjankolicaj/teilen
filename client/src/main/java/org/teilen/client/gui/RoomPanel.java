package org.teilen.client.gui;

import org.teilen.client.queue.PacketQueue;
import org.teilen.client.repository.ClientRepository;
import org.teilen.client.repository.RoomRepository;
import org.teilen.common.domain.Client;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.domain.TextContent;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.content_wrapper.RoomClientsWrapper;
import org.teilen.common.packet.base.content_wrapper.RoomContentWrapper;
import org.teilen.common.packet.meta.RoomOp;
import org.teilen.common.packet.meta.RoomPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.*;


public class RoomPanel extends JPanel {
    HeaderPanel headerPanel;
    BodyPanel bodyPanel;
    FooterPanel footerPanel;

    JLabel noChatLbl;

    public RoomPanel() {
        this.setLayout(new BorderLayout(0, 0));

        this.noChatLbl = new JLabel("    No chat window opened , click an available user and start chatting...");
        this.add(noChatLbl, BorderLayout.CENTER);
    }

    public RoomPanel(Integer clientId) {
        if (clientId != null) {
            this.setLayout(new BorderLayout(0, 0));

            this.headerPanel = new HeaderPanel(clientId);
            this.bodyPanel = new BodyPanel(clientId);
            this.footerPanel = new FooterPanel(clientId);

            this.add(headerPanel, BorderLayout.NORTH);
            this.add(bodyPanel, BorderLayout.CENTER);
            this.add(footerPanel, BorderLayout.SOUTH);
        } else {
            this.setLayout(new BorderLayout(0, 0));

            this.noChatLbl = new JLabel("    No chat window opened , click an available user and start chatting...");
            this.add(noChatLbl, BorderLayout.CENTER);
        }
    }


    public void validateGui() {
        this.bodyPanel.revalidateGui();
    }


    class HeaderPanel extends JPanel {
        Integer clientId;

        JPanel westPanel;
        JPanel eastPanel;

        JLabel usernameLbl;
        JLabel addUserLbl;
        JLabel cameraLbl;
        JLabel phoneLbl;
        Map<String, ImageIcon> iconMap = getIconMap();


        public HeaderPanel(Integer clientId) {
            this.clientId = clientId;
            this.setLayout(new BorderLayout());

            String username = getUsername(ClientRepository.findClientById(clientId));

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
            map.put("User", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-user-30.png")));
            map.put("AddUser", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-user-30.png")));
            map.put("Camera", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-video-camera-30.png")));
            map.put("Phone", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-phone-30.png")));
            return map;
        }

        private String getUsername(Client client) {
            String username = null;
            if (client != null) {
                String firstname = client.getFirstname() != null ? client.getFirstname() : "~";
                String lastname = client.getLastname() != null ? client.getLastname() : "~";
                username = firstname + " " + lastname + " : " + client.getId();
            } else
                username = "";
            return username;
        }
    }


    class BodyPanel extends JPanel {
        Integer clientId;
        Integer ownerId;

        JScrollPane allDaysScrollPane;
        JPanel containerPanel;
        List<DayPanel> dayPanels;


        public BodyPanel(Integer clientId) {
            this.clientId = clientId;
            this.ownerId = ClientRepository.findOwnerId();

            this.containerPanel = new JPanel();
            this.dayPanels = new ArrayList<>();
            this.initGui();

            this.allDaysScrollPane = new JScrollPane(containerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            this.setLayout(new BorderLayout());
            this.add(allDaysScrollPane, BorderLayout.CENTER);

        }

        private void initGui() {
            containerPanel.setLayout(new FlowLayout());
            if (ownerId != null && clientId != null) {
                Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
                if (room != null) {
                    List<RoomContent> roomContents = room.getContents();
                    if (roomContents != null && roomContents.size() != 0) {
                        //create missing day panels
                        for (int i = 0; i < roomContents.size(); i++) {
                            RoomContent roomContent = roomContents.get(i);
                            LocalDate contentDate = roomContent.getCreatedDate();
                            if (contentDate != null) {
                                for (int j = 0; j < dayPanels.size(); j++) {
                                    DayPanel dayPanel = dayPanels.get(i);
                                    LocalDate existingDate = dayPanel.getDate();
                                    if (!existingDate.equals(contentDate)) {
                                        DayPanel newDayPanel = new DayPanel(contentDate, roomContent);
                                        dayPanels.add(newDayPanel);
                                        break;
                                    } else {
                                        dayPanel.addRoomContent(roomContent);
                                    }
                                }
                            }
                        }
                        //Add dayPanels to container
                        for (int i = 0; i < dayPanels.size(); i++) {
                            containerPanel.add(dayPanels.get(i), i);
                        }
                    } else {
                        JLabel emptyRoomContentLbl = new JLabel("Room synced.Start chatting !");
                        containerPanel.add(emptyRoomContentLbl);
                    }

                } else {
                    JLabel emptyRoomContentLbl = new JLabel("Room syncing...");
                    containerPanel.add(emptyRoomContentLbl);

                    //Send read room packet to server
                    Set<Integer> clientIds = getClientIds(ownerId, clientId);
                    RoomPacket rRoomPacket = new RoomPacket(new Header(ownerId, -1), new Body(null, new RoomClientsWrapper(clientIds)), null, RoomOp.ROOM_READ);
                    PacketQueue.writeOut(rRoomPacket);
                }

            }

        }

        private void revalidateGui() {
            if (ownerId != null && clientId != null) {
                Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
                if (room != null) {
                    List<RoomContent> roomContents = room.getContents();
                    if (roomContents != null && roomContents.size() != 0) {

                        //create missing day panels
                        for (int i = 0; i < roomContents.size(); i++) {
                            RoomContent roomContent = roomContents.get(i);
                            LocalDate contentDate = roomContent.getCreatedDate();
                            if (contentDate != null) {
                                for (int j = 0; j < dayPanels.size(); j++) {
                                    DayPanel dayPanel = dayPanels.get(i);
                                    LocalDate existingDate = dayPanel.getDate();
                                    if (!existingDate.equals(contentDate)) {
                                        DayPanel newDayPanel = new DayPanel(contentDate, roomContent);
                                        dayPanels.add(newDayPanel);
                                        break;
                                    } else {
                                        dayPanel.addRoomContent(roomContent);
                                    }
                                }
                            }
                        }

                        //Add dayPanels to container
                        for (int i = dayPanels.size() - 1; i >= 0; i--) {
                            DayPanel dayPanel = dayPanels.get(i);
                            dayPanel.revalidateGui();
                            try {
                                Component component = containerPanel.getComponent(i);
                                if (component == null) {
                                    containerPanel.add(dayPanel, i);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                containerPanel.add(dayPanel, i);
                            }
                        }
                    } else {
                        JLabel emptyRoomContentLbl = new JLabel("Room synced.Start chatting !");
                        containerPanel.add(emptyRoomContentLbl);
                    }
                    this.validate();
                } else {
                    JLabel emptyRoomContentLbl = new JLabel("Room syncing...");
                    containerPanel.add(emptyRoomContentLbl);

                    //Send read room packet to server
                    Set<Integer> clientIds = getClientIds(ownerId, clientId);
                    RoomPacket rRoomPacket = new RoomPacket(new Header(ownerId, -1), new Body(null, new RoomClientsWrapper(clientIds)), null, RoomOp.ROOM_READ);
                    PacketQueue.writeOut(rRoomPacket);
                }
            }
        }

        private Set<Integer> getClientIds(Integer ownerId, Integer... ids) {
            Set<Integer> clientIds = new TreeSet<>();
            clientIds.add(ownerId);
            for (Integer id : ids)
                clientIds.add(id);
            return clientIds;
        }


    }


    class FooterPanel extends JPanel {
        Integer clientId;
        Integer ownerId;

        JPanel centerPanel;
        JPanel eastPanel;

        JLabel addFileLbl;
        JLabel recordLbl;
        JTextField messageTF;
        Map<String, ImageIcon> iconMap = getIconMap();


        public FooterPanel(Integer clientId) {
            this.clientId = clientId;
            this.ownerId = ClientRepository.findOwnerId();
            this.setLayout(new BorderLayout());

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

            //Add listener to message field
            addListeners();
        }


        private Map<String, ImageIcon> getIconMap() {
            Map<String, ImageIcon> map = new HashMap<>();
            map.put("AddFile", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-file-20.png")));
            map.put("Record", new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-add-record-20.png")));
            return map;
        }

        private void addListeners() {
            this.messageTF.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String message = messageTF.getText();
                        messageTF.setText("");
                        sendMessage(message);
                    }
                }
            });
        }

        private void sendMessage(String message) {
            Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
            if (room != null) {
                TextContent textContent = new TextContent(ownerId, message);
                room.addRoomContentByLocal(textContent);
                RoomPacket uRoomPacket = new RoomPacket(new Header(ownerId, -1), new Body(null, new RoomContentWrapper(textContent)), room.getId(), RoomOp.ROOM_UPDATE);
                PacketQueue.writeOut(uRoomPacket);
            } else {
                JOptionPane.showMessageDialog(this, "Room not synced yet.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}



