package org.teilen.common.packet.base;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header implements Serializable {

  private Integer originId;
  private Integer destinationId;


}
