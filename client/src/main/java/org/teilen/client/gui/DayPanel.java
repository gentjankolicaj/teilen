package org.teilen.client.gui;

import org.teilen.common.domain.FileContent;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.domain.TextContent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayPanel extends JPanel {
    private final Integer ownerId;
    private LocalDate date;
    private final List<RoomContent> roomContents;
    private boolean stateChanged;
    private List<RoomContent> tmpRoomContent;
    private final TitledBorder titledBorder;

    public DayPanel(Integer ownerId, LocalDate date, List<RoomContent> roomContents) {
        this.ownerId = ownerId;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        this.roomContents = roomContents;
        this.tmpRoomContent = new ArrayList<>();

        this.titledBorder = new TitledBorder(this.date.toString());
        this.titledBorder.setTitleJustification(TitledBorder.CENTER);
        this.titledBorder.setTitlePosition(TitledBorder.TOP);
        this.setBorder(titledBorder);
        this.stateChanged = true;

    }

    public DayPanel(Integer ownerId, LocalDate date) {
        this.ownerId = ownerId;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.date = date;
        this.roomContents = new ArrayList<>();
        this.tmpRoomContent = new ArrayList<>();

        this.titledBorder = new TitledBorder(this.date.toString());
        this.titledBorder.setTitleJustification(TitledBorder.CENTER);
        this.titledBorder.setTitlePosition(TitledBorder.TOP);
        this.setBorder(titledBorder);
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
                        Integer creatorId = textContent.getCreatorId();

                        RowContent contentBox = null;
                        if (creatorId != null && creatorId.intValue() == ownerId.intValue())
                            contentBox = new RowContent(i, true, textContent);
                        else
                            contentBox = new RowContent(i, false, textContent);

                        this.add(contentBox);
                    }
                }
                tmpRoomContent.clear();
                this.validate();
            }
        }
    }

}

class RowContent extends JPanel {
    int index;
    boolean isOwned;
    RoomContent roomContent;

    JLabel contentLabel;

    public RowContent(int index, boolean isOwned, TextContent textContent) {
        this.index = index;
        this.isOwned = isOwned;
        this.roomContent = textContent;

        if (isOwned) {
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.contentLabel = new JLabel(prepareText(textContent.getText()));
            this.contentLabel.setOpaque(true);
            this.contentLabel.setBackground(new Color(20, 130, 249));
            this.contentLabel.setForeground(Color.white);

        } else {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.contentLabel = new JLabel(prepareText(textContent.getText()));
            this.contentLabel.setOpaque(true);
            this.contentLabel.setBackground(new Color(200, 213, 228));
            this.contentLabel.setForeground(Color.BLACK);

        }
        this.add(contentLabel);

    }

    public RowContent(int index, boolean isOwned, FileContent fileContent) {
        this.index = index;
        this.isOwned = isOwned;
        this.roomContent = fileContent;
    }

    private String prepareText(String text) {
        return "    " + text + "    ";
    }


}
