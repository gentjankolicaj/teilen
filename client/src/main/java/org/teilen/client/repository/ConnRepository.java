package org.teilen.client.repository;

import org.teilen.client.domain.ConnState;
import org.teilen.common.packet.meta.ConnOp;

public class ConnRepository {

    static ConnState connState = ConnState.OFFLINE;

    public static void updateConnState(ConnOp connOp) {
        if (connOp.name().equals(ConnOp.ON.name())) {
            connState = ConnState.ONLINE;
        } else if (connOp.name().equals(ConnOp.OFF.name())) {
            connState = ConnState.OFFLINE;
        }
    }

    public static ConnState findConnState() {
        return connState;
    }

}
