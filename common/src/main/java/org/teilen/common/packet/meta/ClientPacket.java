package org.teilen.common.packet.meta;



import lombok.Data;

import java.io.Serializable;


@Data
public class ClientPacket extends MetaPacket implements Serializable {
    private Integer clientId;
    private ClientOp clientOp;

    public ClientPacket(Integer originId, Integer destinationId, MetaType metaType, Integer clientId, ClientOp clientOp) {
        super(originId, destinationId, metaType);
        this.clientId = clientId;
        this.clientOp = clientOp;
    }
}
