package org.teilen.common.packet.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Packet implements Serializable {
    private Header header;
    private Body body;


}
