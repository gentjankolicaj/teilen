package org.teilen.common.domain;

import lombok.Data;
import org.teilen.common.packet.base.Packet;

import java.io.Serializable;

@Data
public class RoomContent implements Serializable {
    private Integer id;

    public RoomContent(Integer id) {
        this.id = id;
    }

    public RoomContent(Integer id, Packet packet) {
        this.id = id;
    }


}
