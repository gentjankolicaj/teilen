package org.teilen.common.domain;

import lombok.Data;

@Data
public class FileContent extends RoomContent {
    private byte[] array;

    public FileContent(byte[] array) {
        this.array = array;
    }
}
