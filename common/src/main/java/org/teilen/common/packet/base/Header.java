package org.teilen.common.packet.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    private Integer originId;
    private Integer destinationId;


}
