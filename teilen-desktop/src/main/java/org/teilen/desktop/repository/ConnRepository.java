package org.teilen.desktop.repository;

import org.teilen.common.packet.meta.ConnOp;
import org.teilen.desktop.domain.ConnState;

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
