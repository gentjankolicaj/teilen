package org.teilen.common.packet.meta;


import lombok.Data;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;


@Data
public class ClientPacket extends Packet {

  private Integer clientId;
  private ClientOp clientOp;


  public ClientPacket(Integer clientId, ClientOp clientOp) {
    super(null, null);
    this.clientId = clientId;
    this.clientOp = clientOp;
  }

  public ClientPacket(Header header, Integer clientId, ClientOp clientOp) {
    super(header, null);
    this.clientId = clientId;
    this.clientOp = clientOp;
  }

  public ClientPacket(Header header, Body body, Integer clientId, ClientOp clientOp) {
    super(header, body);
    this.clientId = clientId;
    this.clientOp = clientOp;
  }
}
