package org.teilen.desktop.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.teilen.common.domain.FileContent;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.domain.TextContent;

public class DayPanel extends JPanel {

  private final Integer ownerId;
  private final List<RoomContent> roomContents;
  private final TitledBorder titledBorder;
  private LocalDate date;
  private boolean stateChanged;
  private List<RoomContent> tmpRoomContent;

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
      if (tmpGlobalId != null && globalId != null
          && tmpGlobalId.intValue() == globalId.intValue()) {
        found = true;
        break;
      } else if (tmpLocalId != null && localId != null
          && tmpLocalId.intValue() == localId.intValue()) {
        found = true;
        break;
      }
    }

    if (!found && (globalId != null || localId != null)) {
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
          Integer creatorId = roomContent.getCreatorId();

          RowContent contentBox = null;
          if (creatorId != null && creatorId.intValue() == ownerId.intValue()) {
            contentBox = new RowContent(true, roomContent);
          } else {
            contentBox = new RowContent(false, roomContent);
          }

          this.add(contentBox);

        }
        tmpRoomContent.clear();
        this.validate();
      }
    }
  }

}

class RowContent extends JPanel {

  boolean isOwned;
  RoomContent roomContent;
  JLabel textLbl;
  JLabel fileLbl;


  public RowContent(boolean isOwned, RoomContent roomContent) {
    this.isOwned = isOwned;
    this.roomContent = roomContent;
    if (roomContent instanceof TextContent) {
      TextContent textContent = (TextContent) roomContent;
      if (isOwned) {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.textLbl = new JLabel(prepareText(textContent.getText()));
        this.textLbl.setOpaque(true);
        this.textLbl.setBackground(new Color(20, 130, 249));
        this.textLbl.setForeground(Color.white);

      } else {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.textLbl = new JLabel(prepareText(textContent.getText()));
        this.textLbl.setOpaque(true);
        this.textLbl.setBackground(new Color(200, 213, 228));
        this.textLbl.setForeground(Color.BLACK);

      }
      this.add(textLbl);
    } else if (roomContent instanceof FileContent) {
      FileContent fileContent = (FileContent) roomContent;
      String fileName = fileContent.getFileName();
      int fileSize = fileContent.getArray() != null ? fileContent.getArray().length : 0;
      if (isOwned) {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
      } else {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
      }
      this.fileLbl = new JLabel();
      this.fileLbl.setText(prepareFileName(fileName, fileSize));
      this.fileLbl.setIcon(getIcon());
      this.fileLbl.setHorizontalTextPosition(JLabel.RIGHT);
      this.add(fileLbl);
    }

  }


  private static ImageIcon getIcon() {
    return new ImageIcon(RowContent.class.getClassLoader().getResource("icons8-file-20.png"));
  }

  private String prepareFileName(String fileName, int fileSize) {
    if (fileName != null) {
      return fileName + " ~ " + fileSize + " b";
    } else {
      return "tmp ~ " + fileSize + " b";
    }
  }

  private String prepareText(String text) {
    return "    " + text + "    ";
  }


}
