package org.teilen.server.engine;

import org.teilen.server.domain.ClientSocket;
import org.teilen.server.util.LogUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class DiscoveryEngine implements Runnable {
    private final IOEngine ioEngine;
    private final int serverSocketPort = 8888;
    private static int counter = 0;
    private Boolean isRunning = false;
    private ServerSocket serverSocket;
    private final int serverSocketTimeout = 1500;

    public DiscoveryEngine(IOEngine ioEngine) {
        this.ioEngine = ioEngine;
    }

    @Override
    public void run() {
        try {
            LogUtil.info("Waiting to start socket server...");
            while (true) {
                while (isRunning) {
                    try {
                        Socket socket = serverSocket.accept();
                        counter++;
                        this.ioEngine.addUserSocket(new ClientSocket(counter, socket));
                        LogUtil.info("Socket accepted : " + socket.toString());

                    } catch (SocketTimeoutException ste) {
                        LogUtil.warn("Socket server timeout " + serverSocketTimeout + " reached.");

                    } catch (SocketException se) {
                        LogUtil.warn("Socket server closed.");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void startSocketServer() throws IOException {
        synchronized (isRunning) {
            if (isRunning == false) {
                isRunning = true;
                this.serverSocket = new ServerSocket(serverSocketPort);
                this.serverSocket.setSoTimeout(serverSocketTimeout);
                LogUtil.info("Socket server started.Port " + serverSocketPort + ", timeout " + serverSocketTimeout);
            }
        }
    }

    public synchronized void shutdownSocketServer() throws IOException {
        synchronized (isRunning) {
            if (isRunning == true) {
                isRunning = false;
                synchronized (this.serverSocket) {
                    serverSocket.close();
                    serverSocket = null;
                    LogUtil.info("Waiting to start socket server...");
                }
            }
        }
    }
}
