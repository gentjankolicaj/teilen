package org.teilen.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomContent implements Serializable {
    private Integer globalId;
    private Integer localId;

    public RoomContent(Integer globalId, Integer localId) {
        this.globalId = globalId;
        this.localId = localId;
    }


}
