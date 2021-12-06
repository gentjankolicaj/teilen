package org.teilen.server.engine;

import org.teilen.common.domain.Client;
import org.teilen.common.packet.base.Body;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.info.ClientInfo;
import org.teilen.common.packet.media.*;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.common.packet.meta.ConnPacket;
import org.teilen.common.packet.meta.RoomPacket;
import org.teilen.server.gui.ActivityPanel;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.repository.ClientRepository;
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
                    LogUtil.info("AE & GUI : processing started.");
                    processWithGui();
                    LogUtil.info("AE & GUI : processing finished.");
                    Thread.sleep(threadSleep);
                } else {
                    LogUtil.info("AE : processing started.");
                    processWithoutGui();
                    LogUtil.info("AE : processing finished.");
                    long otherSleep = (long) (threadSleep - (threadSleep * 0.9));
                    Thread.sleep(otherSleep);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }


    private void processWithGui() {
        try {
            List<Packet> packets = PacketQueue.readIn(packetNumber);
            if (packets != null && packets.size() != 0) {
                //process in-queue packets and put them into out-queue
                processPackets(packets);
                activityPanel.processGui(packets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processWithoutGui() {
        try {
            List<Packet> packets = PacketQueue.readIn(packetNumber);
            if (packets != null && packets.size() != 0) {
                //process in-queue packets and put them into out-queue
                processPackets(packets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void processPackets(List<Packet> packets) {
        Map<Integer, List<Packet>> clientPacketMap = new HashMap<>();
        List<Packet> allClientsPackets = new ArrayList<>();

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
                    Integer clientId = clientPacket.getClientId();
                    if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_CREATE.name())) {
                        //create 2 packets
                        //1 update existing user with id
                        //2 update other users with new user
                        // -1 => server
                        // 0 => All users
                        // 1-n => specific user
                        //origin -1 => origin :server,
                        //destination 0 => all users
                        Client client = new Client(clientId);
                        ClientRepository.insertClient(client);

                        //Packet to be sent to specific client
                        ClientPacket uClientPacket = new ClientPacket(new Header(-1, clientId), clientPacket.getClientId(), ClientOp.CLIENT_UPDATE);
                        updatePacketMap(clientPacketMap, clientId, uClientPacket);

                        //Packet to be sent to all clients
                        ClientPacket cClientPacket = new ClientPacket(new Header(-1, 0), clientPacket.getClientId(), ClientOp.CLIENT_CREATE);
                        allClientsPackets.add(cClientPacket);
                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_UPDATE.name())) {
                        //origin : user
                        //Destination : all users
                        ClientInfo clientInfo = (ClientInfo) clientPacket.getBody().getContent();
                        Client client = new Client(clientId, clientInfo);
                        ClientRepository.updateClient(client);

                        //Packet to be sent to all clients
                        ClientPacket uClientPacket = new ClientPacket(new Header(clientId, 0), new Body(null, clientInfo), clientPacket.getClientId(), ClientOp.CLIENT_UPDATE);
                        allClientsPackets.add(uClientPacket);

                    } else if (clientPacket.getClientOp().name().equals(ClientOp.CLIENT_DELETE.name())) {
                        //origin : server
                        //Destination : all users
                        ClientPacket dClientPacket = new ClientPacket(new Header(-1, 0), clientId, ClientOp.CLIENT_DELETE);
                        allClientsPackets.add(dClientPacket);
                    }
                }

            }

        }

        Map<Integer, List<Packet>> processedMap = getProcessedMap(clientPacketMap, allClientsPackets);
        PacketQueue.writeOut(processedMap);
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

    private Map<Integer, List<Packet>> getProcessedMap(Map<Integer, List<Packet>> oneClientPacketMap, List<Packet> allClientsPackets) {
        Map<Integer, List<Packet>> processedMap = new HashMap<>();

        return processedMap;
    }


}
