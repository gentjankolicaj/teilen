package org.teilen.client.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketMeta {
    private String socketHost;
    private int socketPort;
    private int timeout;


}
