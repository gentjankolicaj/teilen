package org.teilen.client.engine;

import org.teilen.client.gui.ActivityPanel;
import org.teilen.client.queue.PacketQueue;
import org.teilen.common.packet.Packet;
import org.teilen.common.packet.media.*;
import org.teilen.common.packet.meta.*;

import java.util.ArrayList;
import java.util.List;

public class ActivityEngine implements Runnable {
    private static final int threadSleep = 200; //millis
    private static final int packetNumber = 5;
    private ActivityPanel activityPanel;

    public ActivityEngine() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    List<Packet> packets = PacketQueue.activityReadIn(packetNumber);
                    if (packets != null && packets.size() != 0) {
                        //process in-queue packets and put them into out-queue
                        processPackets(packets);

                        List<Packet> metas = new ArrayList<>();
                        List<Packet> medias = new ArrayList<>();
                        for (int i = 0; i < packets.size(); i++) {
                            Packet packet = packets.get(i);
                            if (packet == null)
                                continue;
                            if (packet instanceof MetaPacket) {
                                metas.add(packet);
                            } else if (packet instanceof MetaPacket) {
                                medias.add(packet);
                            }
                        }
                        if (metas.size() != 0) {
                            activityPanel.updateMeta(metas);
                        }
                    }
                } else {
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
                Thread.sleep(threadSleep);
            } catch (Exception e) {

            }
        }


    }


    private void processPackets(List<Packet> packets) {
        List<Packet> processedPackets = new ArrayList<>();
        for (int i = 0; i < packets.size(); i++) {
            Packet packet = packets.get(i);
            if (packet instanceof MetaPacket) {
                if (packet instanceof ConnPacket) {

                } else if (packet instanceof RoomPacket) {

                } else if (packet instanceof ClientPacket) {
                    ClientPacket clientPacket = (ClientPacket) packet;
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {

                    }
                }

            } else if (packet instanceof MediaPacket) {
                if (packet instanceof TextPacket) {

                } else if (packet instanceof SoundPacket) {

                } else if (packet instanceof VideoPacket) {

                } else if (packet instanceof FilePacket) {

                }
            }
        }
        PacketQueue.activityWriteOut(processedPackets);
    }


    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }
}
