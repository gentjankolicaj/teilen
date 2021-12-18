package org.teilen.common.packet.base.content_wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.common.domain.RoomContent;
import org.teilen.common.packet.base.Content;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomContentWrapper extends Content {
    private List<RoomContent> contents;


    public void addRoomContent(RoomContent roomContent) {
        if (contents == null) {
            contents = new ArrayList<>();
        }
        contents.add(roomContent);
    }

}
