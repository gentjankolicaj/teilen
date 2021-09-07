package org.teilen_webcam.server.engine;

public class ClientDiscovery extends Thread {
    private final long clientSocketTimeout;
    private final String threadName = "ClientDiscovery-Thread";

    public ClientDiscovery(long clientSocketTimeout) {
        this.clientSocketTimeout = clientSocketTimeout;
    }

    public void run() {

    }


}
