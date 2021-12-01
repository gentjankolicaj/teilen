package org.teilen.common.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Packet {
    private Integer originId;
    private Integer destinationId;


}
