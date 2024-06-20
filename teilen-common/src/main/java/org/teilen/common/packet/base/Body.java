package org.teilen.common.packet.base;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body implements Serializable {

  private ContentMetadata contentMetadata;
  private Content content;

}



