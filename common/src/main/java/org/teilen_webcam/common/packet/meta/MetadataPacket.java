package org.teilen_webcam.common.packet.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen_webcam.common.packet.AbstractPacket;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataPacket extends AbstractPacket implements Serializable {
    MetaType metaType;

}
