package org.teilen.common.packet.info;

import lombok.Data;
import org.teilen.common.packet.base.Content;

@Data
public class ClientInfo extends Content {
    private String firstname;
    private String lastname;
}
