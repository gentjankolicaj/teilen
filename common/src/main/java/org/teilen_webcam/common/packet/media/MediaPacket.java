package org.teilen_webcam.common.packet.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen_webcam.common.packet.AbstractPacket;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaPacket extends AbstractPacket implements Serializable {
    public MediaType mediaType;

}
