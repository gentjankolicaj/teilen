package org.teilen_webcam.server.engine;

public class QueueEngine implements Runnable {
    private final ActivityEngine activityEngine;

    public QueueEngine(ActivityEngine activityEngine) {
        this.activityEngine = activityEngine;
    }

    @Override
    public void run() {

    }
}
