package org.teilen.server.engine;

import org.teilen.common.packet.Packet;
import org.teilen.common.packet.meta.MetaPacket;
import org.teilen.server.gui.ActivityPanel;
import org.teilen.server.queue.PacketQueue;

import java.util.ArrayList;
import java.util.List;

public class ActivityEngine implements Runnable {
    private ActivityPanel activityPanel;
    private static int counter;
    private static final int threadSleep = 2000; //millis
    private static final int packerNumber = 5;

    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    Thread.sleep(threadSleep);
                    List<Packet> packets = PacketQueue.readPackets(5);
                    if (packets != null) {
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

                        if (medias.size() != 0) {
                            List<Packet> processedMedias = processMedia(medias);
                            List<Packet> processedMetas = processMetas(metas);
                            PacketQueue.writePackets(processedMedias.toArray(new Packet[processedMedias.size()]));
                            PacketQueue.writePackets(processedMetas.toArray(new Packet[processedMetas.size()]));
                        }

                        if (metas.size() != 0) {
                            activityPanel.updateMeta(metas);
                        }

                    }
                } else {
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
            } catch (Exception e) {

            }
        }

    }


    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }

    private List<Packet> processMedia(List<Packet> medias) {
        return null;
    }

    private List<Packet> processMetas(List<Packet> medias) {
        return null;
    }
}
