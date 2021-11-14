package org.teilen_webcam.server;

import org.teilen_webcam.server.engine.ActivityEngine;
import org.teilen_webcam.server.engine.DiscoveryEngine;
import org.teilen_webcam.server.engine.IOEngine;
import org.teilen_webcam.server.engine.PacketQueue;
import org.teilen_webcam.server.gui.ServerGuiFrame;

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
