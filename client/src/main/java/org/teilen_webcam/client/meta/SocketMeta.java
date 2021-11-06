package org.teilen_webcam.client.meta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketMeta {
    private String socketHost;
    private int socketPort;
    private int timeout;


}
