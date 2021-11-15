package org.teilen.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.Packet;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataPacket extends Packet implements Serializable {
    public MetaType metaType;

}
