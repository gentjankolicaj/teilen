package org.teilen.common.packet.media;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextPacket extends MediaPacket implements Serializable {

  private Long roomId;
  private Long senderId;
  private LocalDateTime sentDateTime;
  private String textContent;


}
