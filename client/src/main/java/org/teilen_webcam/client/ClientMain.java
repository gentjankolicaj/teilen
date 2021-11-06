package org.teilen_webcam.client;

import org.teilen_webcam.client.engine.IOEngine;
import org.teilen_webcam.client.engine.MediaEngine;
import org.teilen_webcam.client.engine.MetadataEngine;
import org.teilen_webcam.client.engine.QueueEngine;
import org.teilen_webcam.client.gui.ClientGuiFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        MetadataEngine metadataEngine = new MetadataEngine();
        MediaEngine mediaEngine = new MediaEngine();
        QueueEngine queueEngine = new QueueEngine(mediaEngine);
        IOEngine ioEngine = new IOEngine(queueEngine);

        ExecutorService executors = Executors.newFixedThreadPool(4);
        executors.submit(metadataEngine);
        executors.submit(mediaEngine);
        executors.submit(queueEngine);
        executors.submit(ioEngine);

        new ClientGuiFrame(ioEngine);
    }

}
