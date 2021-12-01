package org.teilen.common.packet.meta;



import lombok.Data;

import java.io.Serializable;


@Data
public class UserPacket extends MetaPacket implements Serializable {
    private Integer userId;
    private UserOp userOp;

    public UserPacket(Integer originId, Integer destinationId, MetaType metaType, Integer userId, UserOp userOp) {
        super(originId, destinationId, metaType);
        this.userId = userId;
        this.userOp = userOp;
    }
}
