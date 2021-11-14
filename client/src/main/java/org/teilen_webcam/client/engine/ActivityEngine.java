package org.teilen_webcam.client.engine;

import org.teilen_webcam.client.gui.RoomPanel;
import org.teilen_webcam.client.gui.UserPanel;
import org.teilen_webcam.client.queue.PacketQueue;
import org.teilen_webcam.common.packet.AbstractPacket;

import java.util.List;

public class ActivityEngine implements Runnable {
    private final PacketQueue packetQueue;
    private RoomPanel roomPanel;
    private UserPanel userPanel;
    private List<AbstractPacket> out;
    private List<AbstractPacket> in;


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
