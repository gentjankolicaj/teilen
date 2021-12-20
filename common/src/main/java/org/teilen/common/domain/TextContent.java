package org.teilen.common.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TextContent extends RoomContent {
    private String text;

    public TextContent(Integer creatorId, LocalDate createdDate, String text) {
        super.setCreatorId(creatorId);
        super.setCreatedDate(createdDate);
        this.text = text;

    }
}
