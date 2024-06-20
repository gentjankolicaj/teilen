package org.teilen.common.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.wrapper.ClientInfoWrapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

  private Integer id;
  private String firstname;
  private String lastname;

  public Client(Integer id) {
    this.id = id;
  }


  public Client(Integer clientId, ClientInfoWrapper clientInfoWrapper) {
    this.id = clientId;
    this.firstname = clientInfoWrapper.getFirstname();
    this.lastname = clientInfoWrapper.getLastname();
  }
}
