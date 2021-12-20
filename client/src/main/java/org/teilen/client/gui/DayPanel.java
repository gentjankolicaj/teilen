package org.teilen.client.gui;

import org.teilen.common.domain.RoomContent;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayPanel extends JPanel {
    private LocalDate date;
    private List<RoomContent> roomContents;

    public DayPanel(LocalDate date, List<RoomContent> roomContents) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        this.roomContents = roomContents;

        initGui();
    }

    public DayPanel(LocalDate date, RoomContent roomContent) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        if (roomContents == null) {
            this.roomContents = new ArrayList<>();
        }
        this.roomContents.add(roomContent);
        initGui();
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addRoomContent(RoomContent roomContent) {
        if (roomContents == null) {
            this.roomContents = new ArrayList<>();
        }
        this.roomContents.add(roomContent);
    }

    private void setDateLabel() {
        JLabel dateLabel = new JLabel(this.date.toString());
        this.add(dateLabel);
    }

    public void initGui() {
        setDateLabel();
    }

    public void revalidateGui() {

    }

}
