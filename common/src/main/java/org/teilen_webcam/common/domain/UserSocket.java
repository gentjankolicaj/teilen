package org.teilen_webcam.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Socket;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSocket {
    private Integer id;
    private Socket socket;


}