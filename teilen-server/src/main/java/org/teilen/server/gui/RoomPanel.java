package org.teilen.server.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import org.teilen.common.domain.Room;
import org.teilen.server.repository.RoomRepository;

public class RoomPanel extends JPanel {

  private final List<RoomView> roomViews;
  private GridLayout gridLayout;
  private int totalRooms;


  public RoomPanel() {
    this.roomViews = new ArrayList<>();
  }


  private static int getRows(int totalRooms) {
    if (totalRooms != 0) {
      int sqrt = (int) Math.sqrt(totalRooms);
      while ((sqrt * sqrt) <= (totalRooms)) {
        sqrt = sqrt + 1;
      }
      return sqrt;
    } else {
      return 0;
    }

  }

  private static int getColumns(int totalRooms) {
    if (totalRooms != 0) {
      return (int) Math.sqrt(totalRooms);
    } else {
      return 0;
    }
  }


  private void removeComponents() {
    if (totalRooms != 0) {
      int rows = getRows(totalRooms);
      int columns = getColumns(totalRooms);
      this.gridLayout = new GridLayout(rows, columns);
      this.gridLayout.setVgap(5);
      this.gridLayout.setHgap(5);
      this.setLayout(gridLayout);

      for (int i = 0; i < totalRooms; i++) {
        roomViews.remove(i);
        this.remove(i);
      }
    }
  }

  private void setupComponents() {
    List<Room> rooms = RoomRepository.findAllList();
    if (rooms != null && rooms.size() != 0) {
      totalRooms = rooms.size();
      for (Room room : rooms) {
        Integer roomId = room.getId();
        Set<Integer> clientIds = room.getClients();
        String roomName = room.getRoomName();
        RoomView roomView = new RoomView(room.getId(), clientIds.size(), roomName);
        roomViews.add(roomView);
      }

      if (totalRooms != 0) {
        int rows = getRows(totalRooms);
        int columns = getColumns(totalRooms);
        this.gridLayout = new GridLayout(rows, columns);
        this.gridLayout.setVgap(5);
        this.gridLayout.setHgap(5);
        this.setLayout(gridLayout);

        for (int i = 0; i < totalRooms; i++) {
          RoomView roomView = roomViews.get(i);
          this.add(roomView, BorderLayout.CENTER, i);
        }
      }
    }
  }


  public void updateRooms() {
    removeComponents();
    setupComponents();
    this.validate();
  }


}
