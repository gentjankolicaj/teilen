package org.teilen.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPacket extends Packet {

  public RoomOp roomOp;
  private Integer roomId;

  public RoomPacket(Integer roomId, RoomOp roomOp) {
    super(null, null);
    this.roomId = roomId;
    this.roomOp = roomOp;
  }

  public RoomPacket(Header header, Integer roomId, RoomOp roomOp) {
    super(header, null);
    this.roomId = roomId;
    this.roomOp = roomOp;
  }

  public RoomPacket(Header header, Body body, Integer roomId, RoomOp roomOp) {
    super(header, body);
    this.roomId = roomId;
    this.roomOp = roomOp;
  }


}
