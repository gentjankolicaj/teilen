package org.teilen.server;

import org.teilen.server.engine.ActivityEngine;
import org.teilen.server.engine.DiscoveryEngine;
import org.teilen.server.engine.IOEngine;
import org.teilen.server.gui.ServerGuiFrame;
import org.teilen.server.queue.PacketQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        PacketQueue packetQueue = new PacketQueue();

        ActivityEngine activityEngine = new ActivityEngine();
        IOEngine ioEngine = new IOEngine(packetQueue);
        DiscoveryEngine discoveryEngine = new DiscoveryEngine(ioEngine);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(activityEngine);
        executor.submit(discoveryEngine);
        executor.submit(ioEngine);

        new ServerGuiFrame(activityEngine, discoveryEngine);
    }
}
