package org.teilen_webcam.client;

import org.teilen_webcam.client.engine.ActivityEngine;
import org.teilen_webcam.client.engine.IOEngine;
import org.teilen_webcam.client.engine.QueueEngine;
import org.teilen_webcam.client.gui.ClientGuiFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        ActivityEngine activityEngine = new ActivityEngine();
        QueueEngine queueEngine = new QueueEngine(activityEngine);
        IOEngine ioEngine = new IOEngine(queueEngine);

        ExecutorService executors = Executors.newFixedThreadPool(4);
        executors.submit(activityEngine);
        executors.submit(queueEngine);
        executors.submit(ioEngine);

        ClientGuiFrame clientGuiFrame = new ClientGuiFrame(ioEngine);

        activityEngine.setUserPanel(clientGuiFrame.getUserPanel());
        activityEngine.setRoomPanel(clientGuiFrame.getRoomPanel());

    }

}
