package org.teilen.server.gui;

import org.teilen.common.domain.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoomPanel extends JPanel {
    private final List<RoomView> roomViews;
    private final List<Room> rooms;
    private GridLayout gridLayout;
    private int totalRooms;


    public RoomPanel() {
        this.roomViews = new ArrayList<>(totalRooms);
        this.rooms = new ArrayList<>(totalRooms);
    }

    public RoomPanel(int totalRooms) {
        this.totalRooms = totalRooms;
        this.roomViews = new ArrayList<>(totalRooms);
        this.rooms = new ArrayList<>(totalRooms);
        addComponents();
    }

    private static int getRows(int totalRooms) {
        if (totalRooms != 0) {
            int sqrt = (int) Math.sqrt(totalRooms);
            while ((sqrt * sqrt) <= (totalRooms)) {
                sqrt = sqrt + 1;
            }
            return sqrt;
        } else
            return 0;

    }

    private static int getColumns(int totalRooms) {
        if (totalRooms != 0) {
            return (int) Math.sqrt(totalRooms);
        } else
            return 0;
    }

    private void addComponents() {
        if (totalRooms != 0) {
            int rows = getRows(totalRooms);
            int columns = getColumns(totalRooms);
            this.gridLayout = new GridLayout(rows, columns);
            this.gridLayout.setVgap(5);
            this.gridLayout.setHgap(5);
            this.setLayout(gridLayout);

            for (int i = 0; i < totalRooms; i++) {
                RoomView roomView = new RoomView(i);
                this.add(roomView, BorderLayout.CENTER);
            }
        }
    }

    private void setupComponents() {
        if (totalRooms != 0) {
            int rows = getRows(totalRooms);
            int columns = getColumns(totalRooms);
            this.gridLayout = new GridLayout(rows, columns);
            this.gridLayout.setVgap(5);
            this.gridLayout.setHgap(5);
            this.setLayout(gridLayout);

            for (int i = 0; i < totalRooms; i++) {
                RoomView roomView = new RoomView(i);
                this.add(roomView, BorderLayout.CENTER);
            }
        }
    }

    public void addRoom(Room room) {
        if (rooms != null && rooms.size() != 0) {
            Integer roomId = room.getId();
            for (int i = 0; i < rooms.size(); i++) {
                Integer tmpRoomId = rooms.get(i).getId();
                if ((tmpRoomId != null && roomId != null) && !(tmpRoomId.intValue() == roomId.intValue())) {
                    RoomView roomView = new RoomView(roomId);
                    this.rooms.add(room);
                    this.roomViews.add(roomView);
                    break;
                }
            }

        } else {
            RoomView roomView = new RoomView(room.getId());
            this.rooms.add(room);
            this.roomViews.add(roomView);
        }
        this.totalRooms = totalRooms + 1;
        this.setupComponents();
    }

    public void removeRoom(Room room) {
        if (rooms != null && rooms.size() != 0) {
            Integer roomId = room.getId();
            for (int i = 0; i < rooms.size(); i++) {
                Integer tmpRoomId = rooms.get(i).getId();
                if ((tmpRoomId != null && roomId != null) && !(tmpRoomId.intValue() == roomId.intValue())) {
                    rooms.remove(i);
                    RoomView roomView = roomViews.get(i);
                    roomViews.remove(i);
                    this.remove(roomView);
                    break;
                }
            }
        }
        this.totalRooms = totalRooms - 1;
        this.setupComponents();
    }


}
