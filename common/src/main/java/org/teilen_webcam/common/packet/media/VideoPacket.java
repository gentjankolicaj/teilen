package org.teilen_webcam.common.packet.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPacket extends MediaPacket implements Serializable {

    private Long roomId;
    private Long senderId;
    private LocalDateTime sentDateTime;
    private byte[] videoContent;
}
