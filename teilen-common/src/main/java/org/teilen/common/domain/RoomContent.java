package org.teilen.common.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RoomContent implements Serializable {

  private Integer globalId;
  private Integer localId;
  private Integer creatorId;
  private LocalDate createdDate;

  public RoomContent() {
  }

  public RoomContent(Integer globalId) {
    this.globalId = globalId;
  }

  public RoomContent(Integer globalId, Integer localId) {
    this.globalId = globalId;
    this.localId = localId;
  }

  public RoomContent(Integer globalId, Integer localId, Integer creatorId) {
    this.globalId = globalId;
    this.localId = localId;
    this.creatorId = creatorId;
  }

  public RoomContent(Integer globalId, Integer localId, Integer creatorId, LocalDate createdDate) {
    this.globalId = globalId;
    this.localId = localId;
    this.creatorId = creatorId;
    this.createdDate = createdDate;
  }
}
