package org.teilen.server;

import org.teilen.server.engine.ActivityEngine;
import org.teilen.server.engine.IOEngine;
import org.teilen.server.gui.ServerFrame;


public class ServerMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        boolean launchGui = false;
        ActivityEngine activityEngine = new ActivityEngine();
        IOEngine ioEngine = new IOEngine(launchGui);

        ioEngine.start();
        activityEngine.start();

        if (launchGui) {
            new ServerFrame(activityEngine, ioEngine);
        }
    }
}
