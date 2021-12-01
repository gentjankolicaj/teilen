package org.teilen.client;

import org.teilen.client.engine.ActivityEngine;
import org.teilen.client.engine.IOEngine;
import org.teilen.client.gui.ClientFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        ActivityEngine activityEngine = new ActivityEngine();
        IOEngine ioEngine = new IOEngine();

        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.execute(activityEngine);
        executors.execute(ioEngine);

        new ClientFrame(ioEngine, activityEngine);
    }

}
