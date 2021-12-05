package org.teilen.server.engine;

import org.teilen.common.packet.Packet;
import org.teilen.common.packet.media.*;
import org.teilen.common.packet.meta.*;
import org.teilen.server.gui.ActivityPanel;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityEngine extends Thread {
    private ActivityPanel activityPanel;
    private static final int threadSleep = 2000; //millis
    private static final int packetNumber = 5;

    public ActivityEngine() {
        super("ActivityEngine");
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    LogUtil.info("AE: processing started ");
                    process();
                    LogUtil.info("AE: processing finished.");
                    Thread.sleep(threadSleep);
                } else {
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void process() {
        try {
            List<Packet> packets = PacketQueue.readIn(packetNumber);
            if (packets != null && packets.size() != 0) {
                //process in-queue packets and put them into out-queue
                processPackets(packets);

                List<Packet> metas = getMetaPackets(packets);
                if (metas != null && metas.size() != 0) {
                    activityPanel.updateMeta(metas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void processPackets(List<Packet> packets) {
        Map<Integer, List<Packet>> packetMap = new HashMap<>();
        List<Packet> allClientsPacket = new ArrayList<>();

        for (int i = 0; i < packets.size(); i++) {
            Packet packet = packets.get(i);
            if (packet instanceof MetaPacket) {
                if (packet instanceof ConnPacket) {

                } else if (packet instanceof RoomPacket) {

                } else if (packet instanceof ClientPacket) {
                    ClientPacket clientPacket = (ClientPacket) packet;
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
                        //create 2 packets
                        //1 update existing user with id
                        //2 update other users with new user
                        //origin -1 => origin from server,
                        //destination 0 => all users
                        ClientPacket updatePacket = new ClientPacket(-1, clientPacket.getClientId(), MetaType.USER, clientPacket.getClientId(), ClientOp.CLIENT_UPDATE);
                        updatePacketMap(packetMap, clientPacket.getClientId(), updatePacket);

                        allClientsPacket.add(new ClientPacket(-1, 0, MetaType.USER, clientPacket.getClientId(), ClientOp.CLIENT_CREATE));


                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
                        //origin from user
                        //Destination to all users
                        allClientsPacket.add(new ClientPacket(clientPacket.getClientId(), 0, MetaType.USER, clientPacket.getClientId(), ClientOp.CLIENT_UPDATE));

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
                        //origin from server
                        //Destination to all users
                        allClientsPacket.add(new ClientPacket(-1, 0, MetaType.USER, clientPacket.getClientId(), ClientOp.CLIENT_DELETE));
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

        if (allClientsPacket.size() != 0) {
            for (Map.Entry<Integer, List<Packet>> entry : packetMap.entrySet()) {
                List<Packet> clientPackets = entry.getValue();
                clientPackets.addAll(allClientsPacket);
            }
        }

        if (packetMap.size() != 0) {
            PacketQueue.writeOut(packetMap);
        }
    }

    private void updatePacketMap(Map<Integer, List<Packet>> packetMap, Integer clientId, Packet packet) {
        List<Packet> clientPackets = packetMap.get(clientId);
        if (clientPackets == null) {
            clientPackets = new ArrayList<>();
            clientPackets.add(packet);
            packetMap.put(clientId, clientPackets);
        } else {
            clientPackets.add(packet);
        }
    }

    private List<Packet> getMetaPackets(List<Packet> packets) {
        if (packets != null && packets.size() != 0) {
            List<Packet> metas = new ArrayList<>();
            for (Packet packet : packets) {
                if (packet instanceof MetaPacket) {
                    metas.add(packet);
                }
            }
            return metas;
        } else
            return null;
    }


    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }
}
