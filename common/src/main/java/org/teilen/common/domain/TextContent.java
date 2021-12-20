package org.teilen.common.domain;

import lombok.Data;

@Data
public class TextContent extends RoomContent {
    private String text;

    public TextContent(Integer creatorId, String text) {
        super.setCreatorId(creatorId);
        this.text = text;
    }
}
