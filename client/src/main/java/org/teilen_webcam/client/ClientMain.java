package org.teilen_webcam.client;

import org.teilen_webcam.client.engine.ActivityEngine;
import org.teilen_webcam.client.engine.IOEngine;
import org.teilen_webcam.client.gui.ClientGuiFrame;
import org.teilen_webcam.client.queue.PacketQueue;

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
