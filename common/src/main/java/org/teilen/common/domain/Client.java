package org.teilen.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.info.ClientInfo;

import java.io.Serializable;

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


    public Client(Integer clientId, ClientInfo clientInfo) {
        this.id = clientId;
        this.firstname = clientInfo.getFirstname();
        this.lastname = clientInfo.getLastname();
    }
}
