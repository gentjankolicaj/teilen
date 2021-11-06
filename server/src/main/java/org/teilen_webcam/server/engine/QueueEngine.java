package org.teilen_webcam.server.engine;

public class QueueEngine implements Runnable {
    private final RoomEngine roomEngine;

    public QueueEngine(RoomEngine roomEngine) {
        this.roomEngine = roomEngine;
    }

    @Override
    public void run() {

    }
}
