package org.teilen.common.packet.media;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FilePacket extends MediaPacket implements Serializable {

    private Long roomId;
    private Long senderId;
    private LocalDateTime sentDateTime;
    private byte[] fileContent;
    private String fileName;
}
