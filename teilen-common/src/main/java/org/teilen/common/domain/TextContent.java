package org.teilen.common.domain;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TextContent extends RoomContent {

  private String text;

  public TextContent(Integer creatorId, LocalDate createdDate, String text) {
    super.setCreatorId(creatorId);
    super.setCreatedDate(createdDate);
    this.text = text;

  }
}
