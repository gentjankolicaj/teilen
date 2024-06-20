package org.teilen.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.teilen.common.domain.Client;
import org.teilen.common.domain.FileContent;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.domain.TextContent;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.wrapper.RoomClientsWrapper;
import org.teilen.common.packet.base.wrapper.RoomContentWrapper;
import org.teilen.common.packet.meta.RoomOp;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.desktop.global.GlobalConfig;
import org.teilen.desktop.gui.webcam.WebcamFrame;
import org.teilen.desktop.queue.PacketQueue;
import org.teilen.desktop.repository.ClientRepository;
import org.teilen.desktop.repository.RoomRepository;
import org.teilen.desktop.util.LogUtil;


public class RoomPanel extends JPanel {

  HeaderPanel headerPanel;
  BodyPanel bodyPanel;
  FooterPanel footerPanel;
  JLabel noChatLbl;
  WebcamFrame webcamFrame;

  long lastRefreshTime = System.currentTimeMillis();

  public RoomPanel() {
    this.setLayout(new BorderLayout(0, 0));
    this.noChatLbl = new JLabel("Click an available user and start chatting...");
    this.add(noChatLbl, BorderLayout.CENTER);
  }

  public RoomPanel(Integer clientId) {
    if (clientId != null) {
      this.setLayout(new BorderLayout(0, 0));

      this.headerPanel = new HeaderPanel(clientId);
      this.bodyPanel = new BodyPanel(clientId, headerPanel.syncLbl);
      this.footerPanel = new FooterPanel(clientId);

      this.add(headerPanel, BorderLayout.NORTH);
      this.add(bodyPanel, BorderLayout.CENTER);
      this.add(footerPanel, BorderLayout.SOUTH);
    } else {
      this.setLayout(new BorderLayout(0, 0));

      this.noChatLbl = new JLabel("Click an available user and start chatting...");
      this.add(noChatLbl, BorderLayout.CENTER);
    }
  }


  public void validateGui() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastRefreshTime > GlobalConfig.RPRefreshThreshold) {
      if (this.headerPanel != null) {
        this.headerPanel.revalidateGui();
      }
      if (this.bodyPanel != null) {
        this.bodyPanel.revalidateGui();
      }
      if (this.footerPanel != null) {
        this.footerPanel.revalidateGui();
      }
      if (webcamFrame != null) {
        this.webcamFrame.validateGui();
      }

      this.validate();
      LogUtil.info(
          "Room gui refresh time : " + currentTime + " last refresh time : " + lastRefreshTime);
      lastRefreshTime = System.currentTimeMillis();
    }
  }


  class HeaderPanel extends JPanel {

    Integer clientId;

    JPanel westPanel;
    JPanel eastPanel;
    JPanel centerPanel;

    JLabel usernameLbl;
    JLabel addUserLbl;
    JLabel cameraLbl;
    JLabel phoneLbl;
    JLabel syncLbl;
    Map<String, ImageIcon> iconMap = getIconMap();


    public HeaderPanel(Integer clientId) {
      this.clientId = clientId;
      this.setLayout(new BorderLayout());

      String username = getUsername(ClientRepository.findClientById(clientId));

      this.westPanel = new JPanel();
      this.eastPanel = new JPanel();
      this.centerPanel = new JPanel();

      this.usernameLbl = new JLabel(username);
      this.addUserLbl = new JLabel("  ");
      this.cameraLbl = new JLabel("  ");
      this.phoneLbl = new JLabel("  ");
      this.syncLbl = new JLabel("Room syncing...");

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

      this.centerPanel.add(syncLbl);

      //Set panels east & west
      this.add(westPanel, BorderLayout.WEST);
      this.add(eastPanel, BorderLayout.EAST);
      this.add(centerPanel, BorderLayout.CENTER);

      //Add contentPane to parent panel
      addComponentListeners();
    }


    private Map<String, ImageIcon> getIconMap() {
      Map<String, ImageIcon> map = new HashMap<>();
      map.put("User",
          new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-user-30.png")));
      map.put("AddUser", new ImageIcon(
          RealtimePanel.class.getClassLoader().getResource("icons8-add-user-30.png")));
      map.put("Camera", new ImageIcon(
          RealtimePanel.class.getClassLoader().getResource("icons8-video-camera-30.png")));
      map.put("Phone",
          new ImageIcon(RealtimePanel.class.getClassLoader().getResource("icons8-phone-30.png")));
      return map;
    }

    private String getUsername(Client client) {
      String username = null;
      if (client != null) {
        String firstname = client.getFirstname() != null ? client.getFirstname() : "~";
        String lastname = client.getLastname() != null ? client.getLastname() : "~";
        username = firstname + " " + lastname + " : " + client.getId();
      } else {
        username = "";
      }
      return username;
    }

    public void revalidateGui() {
      String recentUsername = getUsername(ClientRepository.findClientById(clientId));
      usernameLbl.setText(recentUsername);
    }

    private void addComponentListeners() {
      this.cameraLbl.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          webcamFrame = new WebcamFrame();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
      });

      this.addUserLbl.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          JOptionPane.showMessageDialog(HeaderPanel.this, "Functionality not implemented.", "Alert",
              JOptionPane.WARNING_MESSAGE);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
      });

      this.phoneLbl.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          JOptionPane.showMessageDialog(HeaderPanel.this, "Functionality not implemented.", "Alert",
              JOptionPane.WARNING_MESSAGE);

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
      });
    }
  }


  class BodyPanel extends JPanel {

    Integer clientId;
    Integer ownerId;

    JLabel syncLbl;
    JScrollPane allDaysScrollPane;
    JPanel containerPanel;
    List<DayPanel> dayPanels;

    boolean syncedLabelFlag;
    boolean removeInitLabels = true;

    public BodyPanel(Integer clientId, JLabel syncLbl) {
      this.clientId = clientId;
      this.ownerId = ClientRepository.findOwnerId();
      this.syncLbl = syncLbl;

      this.containerPanel = new JPanel();
      this.dayPanels = new ArrayList<>();
      this.init();

      this.allDaysScrollPane = new JScrollPane(containerPanel,
          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

      this.setLayout(new BorderLayout());
      this.add(allDaysScrollPane, BorderLayout.CENTER);
    }

    private void init() {
      this.containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
      if (ownerId != null && clientId != null) {
        Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
        if (room != null) {
          List<RoomContent> roomContents = room.getContents();
          if (roomContents != null && roomContents.size() != 0) {
            //create missing day panels
            for (int i = 0; i < roomContents.size(); i++) {
              RoomContent roomContent = roomContents.get(i);
              LocalDate createdDate = roomContent.getCreatedDate();
              if (createdDate != null) {
                if (dayPanels.size() != 0) {
                  boolean foundDate = false;
                  for (int j = 0; j < dayPanels.size(); j++) {
                    DayPanel dayPanel = dayPanels.get(j);
                    LocalDate existingDate = dayPanel.getDate();
                    if (existingDate.equals(createdDate)) {
                      dayPanel.addRoomContent(roomContent);
                      foundDate = true;
                      break;
                    }
                  }
                  if (!foundDate) {
                    DayPanel dayPanel = new DayPanel(ownerId, createdDate);
                    dayPanel.addRoomContent(roomContent);
                    dayPanels.add(dayPanel);
                  }

                } else {
                  DayPanel dayPanel = new DayPanel(ownerId, createdDate);
                  dayPanel.addRoomContent(roomContent);
                  dayPanels.add(dayPanel);
                }
              }
            }
            //Add dayPanels to container
            for (int i = 0; i < dayPanels.size(); i++) {
              containerPanel.add(dayPanels.get(i), i);
            }
          } else {
            syncLbl.setText("Room synced");
          }

        } else {
          //Send read room packet to server
          Set<Integer> clientIds = getClientIds(ownerId, clientId);
          RoomPacket rRoomPacket = new RoomPacket(new Header(ownerId, -1),
              new Body(null, new RoomClientsWrapper(clientIds)), null, RoomOp.ROOM_READ);
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
            if (removeInitLabels) {
              containerPanel.removeAll();
              removeInitLabels = false;
            }

            //create missing day panels
            for (int i = 0; i < roomContents.size(); i++) {
              RoomContent roomContent = roomContents.get(i);
              LocalDate createdDate = roomContent.getCreatedDate();
              if (createdDate != null) {
                if (dayPanels.size() != 0) {
                  boolean foundDate = false;
                  for (int j = 0; j < dayPanels.size(); j++) {
                    DayPanel dayPanel = dayPanels.get(j);
                    LocalDate existingDate = dayPanel.getDate();
                    if (existingDate.equals(createdDate)) {
                      dayPanel.addRoomContent(roomContent);
                      foundDate = true;
                      break;
                    }
                  }
                  if (!foundDate) {
                    DayPanel dayPanel = new DayPanel(ownerId, createdDate);
                    dayPanel.addRoomContent(roomContent);
                    dayPanels.add(dayPanel);
                  }

                } else {
                  DayPanel dayPanel = new DayPanel(ownerId, createdDate);
                  dayPanel.addRoomContent(roomContent);
                  dayPanels.add(dayPanel);
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
            if (!syncedLabelFlag) {
              syncLbl.setText("Room synced");
              syncedLabelFlag = true;
            }
          }
        }
      }
      this.allDaysScrollPane.validate();
    }

    private Set<Integer> getClientIds(Integer ownerId, Integer... ids) {
      Set<Integer> clientIds = new TreeSet<>();
      clientIds.add(ownerId);
      Collections.addAll(clientIds, ids);
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

      this.addFileLbl.setIcon(getAddFileIcon());
      this.recordLbl.setIcon(getRecordIcon());

      //Add usernameLbl to westPanel
      this.centerPanel.add(messageTF, BorderLayout.CENTER);

      //Other Lbl to east panel
      this.eastPanel.add(addFileLbl);
      this.eastPanel.add(recordLbl);

      //Set panels east & west
      this.add(centerPanel, BorderLayout.CENTER);
      this.add(eastPanel, BorderLayout.EAST);

      //Add listener to message field
      addComponentListeners();
    }


    private ImageIcon getAddFileIcon() {
      return new ImageIcon(
          RealtimePanel.class.getClassLoader().getResource("icons8-add-file-20.png"));
    }

    private ImageIcon getRecordIcon() {
      return new ImageIcon(
          RealtimePanel.class.getClassLoader().getResource("icons8-add-record-20.png"));
    }


    private void addComponentListeners() {

      this.messageTF.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
          }
        }
      });

      this.addFileLbl.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          sendFile();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
      });

      this.recordLbl.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          JOptionPane.showMessageDialog(FooterPanel.this, "Functionality not implemented.", "Alert",
              JOptionPane.WARNING_MESSAGE);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
      });
    }


    //todo: bugfix sometimes messages are not sent after pressing sent
    private void sendMessage() {
      String message = messageTF.getText();
      messageTF.setText("");
      Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
      if (room != null) {
        TextContent textContent = new TextContent(ownerId, LocalDate.now(), message);
        room.addRoomContentByLocal(textContent);
        RoomPacket uRoomPacket = new RoomPacket(new Header(ownerId, -1),
            new Body(null, new RoomContentWrapper(textContent)), room.getId(), RoomOp.ROOM_UPDATE);
        PacketQueue.writeOut(uRoomPacket);
      } else {
        JOptionPane.showMessageDialog(this, "Room not synced yet.", "Alert",
            JOptionPane.WARNING_MESSAGE);
      }
    }

    private FileContent loadFile() {
      FileContent fileContent = null;
      try {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "png,jpg,jpeg,gif,txt,pdf files", "png", "jpg", "jpeg", "gif", "txt", "pdf");
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = jFileChooser.getSelectedFile();
          if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            LogUtil.info("Chosen file : " + fileName);

            byte[] array = new byte[(int) file.length()];

            //open fis and write file content to array
            FileInputStream fis = new FileInputStream(file);
            fis.read(array);
            fis.close();

            fileContent = new FileContent(ownerId, LocalDate.now(), fileName, array);

          } else {
            LogUtil.info("Chosen file not exits or no file at all.");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return fileContent;
    }

    private void sendFile() {
      Room room = RoomRepository.findRoomByClientIds(ownerId, clientId);
      if (room != null) {
        FileContent fileContent = loadFile();
        room.addRoomContentByLocal(fileContent);

        RoomPacket uRoomPacket = new RoomPacket(new Header(ownerId, -1),
            new Body(null, new RoomContentWrapper(fileContent)), room.getId(), RoomOp.ROOM_UPDATE);
        PacketQueue.writeOut(uRoomPacket);
      } else {
        JOptionPane.showMessageDialog(this, "Room not synced yet.", "Alert",
            JOptionPane.WARNING_MESSAGE);
      }

    }

    public void revalidateGui() {
    }
  }

}



