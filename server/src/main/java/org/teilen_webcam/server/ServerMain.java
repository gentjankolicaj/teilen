package org.teilen_webcam.server;

import org.teilen_webcam.server.engine.IOEngine;
import org.teilen_webcam.server.engine.MetadataEngine;
import org.teilen_webcam.server.engine.QueueEngine;
import org.teilen_webcam.server.engine.RoomEngine;
import org.teilen_webcam.server.gui.ServerGuiFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        MetadataEngine metadataEngine = new MetadataEngine();
        RoomEngine mediaThread = new RoomEngine();
        QueueEngine queueEngine = new QueueEngine(mediaThread);
        IOEngine ioEngine = new IOEngine(queueEngine);

        ExecutorService executors = Executors.newFixedThreadPool(4);
        executors.submit(metadataEngine);
        executors.submit(mediaThread);
        executors.submit(queueEngine);
        executors.submit(ioEngine);

        new ServerGuiFrame();

    }
}
