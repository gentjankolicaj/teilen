package org.teilen.common.packet.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Packet;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaPacket extends Packet {

  public MediaType mediaType;

}
