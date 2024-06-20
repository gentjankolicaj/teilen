package org.teilen.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Packet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnPacket extends Packet {

  public ConnOp connOp;
}
