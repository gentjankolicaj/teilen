package org.teilen_webcam.server;

import org.teilen_webcam.server.engine.*;
import org.teilen_webcam.server.gui.ServerGuiFrame;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        MetadataEngine metadataEngine = new MetadataEngine();
        ActivityEngine mediaThread = new ActivityEngine();
        QueueEngine queueEngine = new QueueEngine(mediaThread);
        IOEngine ioEngine = new IOEngine(queueEngine);
        DiscoveryEngine discoveryEngine = new DiscoveryEngine(ioEngine);

        List<Runnable> engines = Arrays.asList(metadataEngine, mediaThread, queueEngine, ioEngine, discoveryEngine);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (Runnable engine : engines) {
            executor.submit(engine);
        }

        new ServerGuiFrame(engines, executor);
    }
}
