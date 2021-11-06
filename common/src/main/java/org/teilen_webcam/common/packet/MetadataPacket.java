package org.teilen_webcam.common.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataPacket extends AbstractPacket implements Serializable {
    private MetaType metaType;

}
