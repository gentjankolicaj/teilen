package org.teilen.common.packet.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.Packet;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaPacket extends Packet implements Serializable {
    public MediaType mediaType;

}
