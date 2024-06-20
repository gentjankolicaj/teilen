package org.teilen.common.packet.base.wrapper;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.packet.base.Content;


@Data
public class RoomContentWrapper extends Content {

  private List<RoomContent> roomContents;

  public RoomContentWrapper(List<RoomContent> roomContents) {
    this.roomContents = roomContents;
  }

  public RoomContentWrapper(RoomContent roomContent) {
    if (roomContents == null) {
      roomContents = new ArrayList<>();
    }
    this.roomContents.add(roomContent);
  }

  public void addRoomContent(RoomContent roomContent) {
    if (roomContents == null) {
      roomContents = new ArrayList<>();
    }
    roomContents.add(roomContent);
  }

}
