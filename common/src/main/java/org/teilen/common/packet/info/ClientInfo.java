package org.teilen.common.packet.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.packet.base.Content;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo extends Content {
    private String firstname;
    private String lastname;
}
