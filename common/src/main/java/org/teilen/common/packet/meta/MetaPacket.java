package org.teilen.common.packet.meta;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.Packet;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MetaPacket extends Packet implements Serializable {
    private MetaType metaType;

    public MetaPacket(Integer originId, Integer destinationId, MetaType metaType) {
        super(originId, destinationId);
        this.metaType = metaType;
    }
}
