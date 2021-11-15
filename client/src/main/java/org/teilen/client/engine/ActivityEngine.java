package org.teilen.client.engine;

import org.teilen.client.gui.RoomPanel;
import org.teilen.client.gui.UserPanel;
import org.teilen.client.queue.PacketQueue;
import org.teilen.common.packet.Packet;

import java.util.List;

public class ActivityEngine implements Runnable {
    private final PacketQueue packetQueue;
    private RoomPanel roomPanel;
    private UserPanel userPanel;
    private List<Packet> out;
    private List<Packet> in;


    public ActivityEngine(PacketQueue packetQueue) {
        this.packetQueue = packetQueue;
    }

    @Override
    public void run() {
        while (true) {


        }

    }


    public void setPanels(UserPanel userPanel, RoomPanel roomPanel) {
        this.roomPanel = roomPanel;
        this.userPanel = userPanel;
    }

}
