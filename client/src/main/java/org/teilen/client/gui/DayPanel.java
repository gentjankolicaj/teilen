package org.teilen.client.gui;

import org.teilen.common.domain.RoomContent;
import org.teilen.common.domain.TextContent;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayPanel extends JPanel {
    private LocalDate date;
    private final List<RoomContent> roomContents;
    JLabel dateLabel;
    private boolean stateChanged;
    private List<RoomContent> tmpRoomContent;

    public DayPanel(LocalDate date, List<RoomContent> roomContents) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        this.roomContents = roomContents;
        this.tmpRoomContent = new ArrayList<>();
        this.dateLabel = new JLabel(this.date.toString());
        this.add(dateLabel, 0);

        this.stateChanged = true;

    }

    public DayPanel(LocalDate date, RoomContent roomContent) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        this.roomContents = new ArrayList<>();
        this.tmpRoomContent = new ArrayList<>();

        this.roomContents.add(roomContent);
        this.dateLabel = new JLabel(this.date.toString());
        this.add(dateLabel, 0);

        this.stateChanged = true;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addRoomContent(RoomContent roomContent) {
        if (tmpRoomContent == null) {
            this.tmpRoomContent = new ArrayList<>();
        }
        boolean found = false;
        Integer globalId = roomContent.getGlobalId();
        Integer localId = roomContent.getLocalId();
        for (RoomContent var : roomContents) {
            Integer tmpGlobalId = var.getGlobalId();
            Integer tmpLocalId = var.getLocalId();
            if (tmpGlobalId != null && globalId != null && tmpGlobalId.intValue() == globalId.intValue()) {
                found = true;
                break;
            } else if (tmpLocalId != null && localId != null && tmpLocalId.intValue() == localId.intValue()) {
                found = true;
                break;
            }
        }

        if (found == false && (globalId != null || localId != null)) {
            this.tmpRoomContent.add(roomContent);
            this.stateChanged = true;
        }
    }


    public void revalidateGui() {
        if (stateChanged) {
            if (tmpRoomContent.size() != 0) {
                roomContents.addAll(tmpRoomContent);
                for (int i = 0; i < tmpRoomContent.size(); i++) {
                    RoomContent roomContent = tmpRoomContent.get(i);
                    if (roomContent instanceof TextContent) {
                        TextContent textContent = (TextContent) roomContent;
                        TextLabel textLabel = new TextLabel(textContent);
                        textLabel.setHorizontalAlignment(SwingConstants.LEFT);
                        this.add(textLabel);
                    }
                }
                tmpRoomContent.clear();
                this.validate();
            }
        }
    }

}
