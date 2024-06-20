package org.teilen.common.packet.base.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.domain.Room;
import org.teilen.common.packet.base.Content;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomWrapper extends Content {

  private Room room;


}
