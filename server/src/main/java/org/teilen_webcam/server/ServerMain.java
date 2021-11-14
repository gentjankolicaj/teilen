package org.teilen_webcam.server;

import org.teilen_webcam.server.engine.ActivityEngine;
import org.teilen_webcam.server.engine.DiscoveryEngine;
import org.teilen_webcam.server.engine.IOEngine;
import org.teilen_webcam.server.engine.QueueEngine;
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
        ActivityEngine activityEngine = new ActivityEngine();
        QueueEngine queueEngine = new QueueEngine(activityEngine);
        IOEngine ioEngine = new IOEngine(queueEngine);
        DiscoveryEngine discoveryEngine = new DiscoveryEngine(ioEngine);
        List<Runnable> engines = Arrays.asList(activityEngine, queueEngine, ioEngine, discoveryEngine);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (Runnable engine : engines) {
            executor.submit(engine);
        }

        ServerGuiFrame serverGuiFrame = new ServerGuiFrame(engines);

        activityEngine.setActivityPanel(serverGuiFrame.getActivityPanel());
    }
}
