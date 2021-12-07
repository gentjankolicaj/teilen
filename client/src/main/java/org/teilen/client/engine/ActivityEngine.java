package org.teilen.client.engine;

import org.teilen.client.gui.ActivityPanel;
import org.teilen.client.gui.InfoPanel;
import org.teilen.client.queue.PacketQueue;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.media.*;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnPacket;
import org.teilen.common.packet.meta.RoomPacket;

import java.util.ArrayList;
import java.util.List;

public class ActivityEngine implements Runnable {
    private static final int threadSleep = 200; //millis
    private static final int packetNumber = 5;

    //Panels to
    private ActivityPanel activityPanel;
    private InfoPanel infoPanel;

    public ActivityEngine() {
    }

    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    List<Packet> packets = PacketQueue.readIn(packetNumber);
                    if (packets != null && packets.size() != 0) {
                        //process in-queue packets and put them into out-queue
                        processPackets(packets);

                        //Process changes at gui
                        activityPanel.processGui(packets);
                        infoPanel.processGui(packets);
                    }
                } else {
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
                Thread.sleep(threadSleep);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void processPackets(List<Packet> packets) {
        List<Packet> processedPackets = new ArrayList<>();
        for (int i = 0; i < packets.size(); i++) {
            Packet packet = packets.get(i);
            if (packet instanceof MediaPacket) {
                if (packet instanceof TextPacket) {

                } else if (packet instanceof SoundPacket) {

                } else if (packet instanceof VideoPacket) {

                } else if (packet instanceof FilePacket) {

                }
            } else {
                if (packet instanceof ConnPacket) {

                } else if (packet instanceof RoomPacket) {

                } else if (packet instanceof ClientPacket) {
                    ClientPacket clientPacket = (ClientPacket) packet;
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {

                    }
                }
            }
        }
        PacketQueue.writeOut(processedPackets);
    }


}
