package org.teilen.desktop;

import org.teilen.desktop.engine.ActivityEngine;
import org.teilen.desktop.engine.IOEngine;
import org.teilen.desktop.gui.ClientFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DesktopApplication {

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
