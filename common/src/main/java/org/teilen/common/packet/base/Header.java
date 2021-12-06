package org.teilen.common.packet.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header implements Serializable {
    private Integer originId;
    private Integer destinationId;


}
