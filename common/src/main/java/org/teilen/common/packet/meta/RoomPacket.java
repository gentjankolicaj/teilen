package org.teilen.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Packet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPacket extends Packet {
    public RoomOp roomOp;
    private Integer roomId;


}
