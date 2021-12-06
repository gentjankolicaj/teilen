package org.teilen.common.packet.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body implements Serializable {
    private Metadata metadata;
    private Content content;

}



