package org.teilen.desktop.gui;

import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import org.teilen.common.domain.Client;
import org.teilen.desktop.repository.ClientRepository;

public class UserPanel extends JList<String> {

  static DefaultListModel<String> userModelList = new DefaultListModel<>();
  private final ImageIcon userImageIcon = getUserImageIcon();

  public UserPanel() {
    super(userModelList);
    this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.setCellRenderer(new ClientIconRenderer());
  }

  public static ImageIcon getUserImageIcon() {
    return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-user-30.png"));
  }

  private static ImageIcon getRoomImageIcon() {
    return new ImageIcon(ActivityPanel.class.getClassLoader().getResource("icons8-group-30.png"));
  }

  public void updateClients() {
    userModelList.clear();
    List<Client> clients = ClientRepository.findAllList();
    if (clients != null && clients.size() != 0) {
      for (int i = 0; i < clients.size(); i++) {
        Client client = clients.get(i);
        String username = getUsername(client);
        userModelList.add(i, username);
      }
    }
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


  public Integer getClientId(int selectedIndex) {
    List<Client> clients = ClientRepository.findAllList();
    if (clients != null && clients.size() != 0 && selectedIndex != -1) {
      return clients.get(selectedIndex).getId();
    } else {
      return null;
    }
  }


  class ClientIconRenderer extends DefaultListCellRenderer {

    Font font = new Font("helvitica", Font.BOLD, 13);

    @Override
    public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {

      JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
          cellHasFocus);
      String username = label.getText();
      username = username + " ";
      label.setText(username);
      label.setIcon(userImageIcon);
      label.setHorizontalTextPosition(JLabel.RIGHT);
      label.setFont(font);
      return label;
    }
  }

}
