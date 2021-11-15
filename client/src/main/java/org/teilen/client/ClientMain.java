package org.teilen.client;

import org.teilen.client.engine.ActivityEngine;
import org.teilen.client.engine.IOEngine;
import org.teilen.client.gui.ClientGuiFrame;
import org.teilen.client.queue.PacketQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        PacketQueue packetQueue = new PacketQueue();
        ActivityEngine activityEngine = new ActivityEngine(packetQueue);
        IOEngine ioEngine = new IOEngine(packetQueue);

        ExecutorService executors = Executors.newFixedThreadPool(4);
        executors.submit(activityEngine);
        executors.submit(ioEngine);

        new ClientGuiFrame(ioEngine, activityEngine);

    }

}
