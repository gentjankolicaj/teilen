package org.teilen.client.gui;

import org.teilen.client.queue.PacketQueue;
import org.teilen.client.repository.ClientRepository;
import org.teilen.client.repository.RoomRepository;
import org.teilen.common.domain.Room;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.meta.RoomOp;
import org.teilen.common.packet.meta.RoomPacket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BodyPanel extends JPanel {
    Integer clientId;

    JScrollPane daysScrollPane;
    JPanel daysPanel;


    public BodyPanel(Integer clientId) {
        this.clientId = clientId;

        this.daysPanel = createAllDaysPanel();

        this.daysScrollPane = new JScrollPane(daysPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(daysScrollPane);

    }

    private JPanel createAllDaysPanel() {
        JPanel parentPanel = new JPanel();
        daysPanel.setLayout(new FlowLayout());
        Integer ownerId = ClientRepository.findOwnerId();
        if (ownerId != null && clientId != null) {
            Room room = RoomRepository.findRoomByUserIds(ownerId, clientId);
            if (room != null) {
                List<RoomContent> roomContents = room.getContents();
                if (roomContents != null && roomContents.size() != 0) {
                    List<DayPanel> dayPanels = new ArrayList<>();
                    for (int i = 0; i < roomContents.size(); i++) {

                    }
                } else {
                    JLabel emptyRoomContentLbl = new JLabel("Room has no content.Start chatting ...");
                    parentPanel.add(emptyRoomContentLbl);
                }

            } else {
                RoomPacket readRoomPacket = new RoomPacket(new Header(ownerId, -1), null, RoomOp.ROOM_READ);
                PacketQueue.writeOut(readRoomPacket);
            }

        }

        return parentPanel;
    }




}
