package org.teilen.common.domain;

import java.time.LocalDate;
import lombok.Data;

@Data
public class FileContent extends RoomContent {

  private String fileName;
  private byte[] array;

  public FileContent(String fileName, byte[] array) {
    this.fileName = fileName;
    this.array = array;
  }

  public FileContent(Integer creatorId, LocalDate createdDate, String fileName, byte[] array) {
    super.setCreatorId(creatorId);
    super.setCreatedDate(createdDate);
    this.fileName = fileName;
    this.array = array;

  }
}
