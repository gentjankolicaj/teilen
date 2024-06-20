package org.teilen.common.packet.base.wrapper;

import java.util.Set;
import java.util.TreeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Content;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomClientsWrapper extends Content {

  private Set<Integer> clientIds;


  public void addClientId(Integer clientId) {
    if (clientIds == null) {
      clientIds = new TreeSet<>();
    }
    clientIds.add(clientId);
  }

}
