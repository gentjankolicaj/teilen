package org.teilen.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPacket extends MetaPacket implements Serializable {
    public RoomOp roomOp;
    private Integer roomId;


}
