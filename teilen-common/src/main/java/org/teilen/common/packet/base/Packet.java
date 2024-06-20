package org.teilen.common.packet.base;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Packet implements Serializable {

  private Header header;
  private Body body;


}
