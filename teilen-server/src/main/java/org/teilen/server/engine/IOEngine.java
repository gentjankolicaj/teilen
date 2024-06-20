package org.teilen.server.engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.teilen.common.packet.base.Header;
import org.teilen.common.packet.base.Packet;
import org.teilen.common.packet.comm.Request;
import org.teilen.common.packet.comm.Response;
import org.teilen.common.packet.comm.Status;
import org.teilen.common.packet.meta.ClientOp;
import org.teilen.common.packet.meta.ClientPacket;
import org.teilen.server.domain.ClientSocket;
import org.teilen.server.queue.PacketQueue;
import org.teilen.server.util.LogUtil;

public class IOEngine extends Thread {

  private static final int threadSleep = 2000; //millis
  private static final int packetNumber = 5;
  private static final long readWriteTimeout = 100;
  private static final List<ClientSocket> clientSockets = new CopyOnWriteArrayList<>();

  private DiscoveryEngine discoveryEngine;
  private Boolean discoveryEngineFlag = false;

  public IOEngine(boolean gui) {
    super("IOEngine");
    this.discoveryEngineFlag = !gui;
  }

  private static void removeClientSocket(ClientSocket clientSocket) {
    if (clientSocket != null) {
      clientSocket.close();
      ClientPacket clientPacket = new ClientPacket(new Header(-1, 0), clientSocket.getClientId(),
          ClientOp.CLIENT_DELETE);
      PacketQueue.writeIn(clientPacket);
    }
  }

  public static void removeClientSockets(List<Integer> clientSocketIndex) {
    //Remove client sockets with issues
    for (Integer idx : clientSocketIndex) {
      ClientSocket clientSocket = clientSockets.get(idx);
      clientSockets.remove(idx.intValue());
      removeClientSocket(clientSocket);
    }
  }

  public static void addClientSocket(ClientSocket clientSocket) throws IOException {
    if (clientSocket != null) {
      clientSockets.add(clientSocket);
      ClientPacket clientPacket = new ClientPacket(new Header(-1, 0), clientSocket.getClientId(),
          ClientOp.CLIENT_CREATE);
      PacketQueue.writeIn(clientPacket);
      LogUtil.info("Socket accepted : " + clientSocket);

    }
  }

  public static void removeAllClientSockets() {
    if (clientSockets.size() != 0) {
      for (ClientSocket clientSocket : clientSockets) {
        try {
          clientSocket.close();
          LogUtil.warn(clientSocket + " closed.");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      clientSockets.removeAll(clientSockets);
    }
  }

  public void run() {
    while (true) {
      try {
        while (discoveryEngineFlag) {
          if (discoveryEngine == null) {
            this.discoveryEngine = new DiscoveryEngine();
            this.discoveryEngine.start();
          }

          LogUtil.info("IOE: started.");
          int totalClients = clientSockets.size();

          List<Integer> removeClientsIndex = new ArrayList<>();
          for (int i = 0; i < totalClients; i++) {
            try {
              ClientSocket clientSocket = clientSockets.get(i);
              Integer clientId = clientSocket.getClientId();
              ObjectOutputStream out = clientSocket.getOut();
              ObjectInputStream in = clientSocket.getIn();

              List<Packet> serverPackets = PacketQueue.readOut(clientId, packetNumber);
              LogUtil.info("Server : packets " + serverPackets);
              int serverPacketsNr = serverPackets != null ? serverPackets.size() : 0;
              Request firstRequest = new Request(0, serverPacketsNr);
              out.writeObject(firstRequest);
              out.flush();

              Object inObject = in.readObject();
              long start = System.currentTimeMillis();
              long diff = start;
              while (inObject == null) {
                inObject = in.readObject();
                diff = System.currentTimeMillis();
                if (diff - start <= readWriteTimeout) {
                  LogUtil.info(
                      "Server : - failed to receive Proposal-Response from client.Timeout " + (diff
                          - start));
                  break;
                }
              }
              if (inObject instanceof Response) {
                Response firstResponse = (Response) inObject;
                LogUtil.info("Server : To client sent " + firstRequest);
                LogUtil.info("Server : From client received " + firstResponse);

                if (firstResponse.getStatus().name().equals(Status.OK.name())) {
                  for (int j = 0; j < serverPacketsNr; j++) {
                    Packet packet = serverPackets.get(j);
                    out.writeObject(packet);
                  }
                  out.flush();
                  Object outPacket = in.readObject();
                  start = System.currentTimeMillis();
                  diff = start;
                  while (outPacket == null) {
                    outPacket = in.readObject();
                    diff = System.currentTimeMillis();
                    if (diff - start <= readWriteTimeout) {
                      LogUtil.info("Server : - failed to sent packet to client , client-response "
                          + outPacket + ".Sending stopped.Timeout : " + (diff - start));
                      break;
                    }
                  }
                  Response packetResponse = null;
                  if (outPacket instanceof Response) {
                    packetResponse = (Response) outPacket;
                    LogUtil.info("Server : - client-response " + packetResponse);
                  } else {
                    LogUtil.warn("Server : stopped sending packets , client-response " + outPacket);
                  }
                }

              } else {
                inObject = null;
              }

              //Receiving from client to teilen-server
              inObject = in.readObject();
              diff = start;
              while (inObject == null) {
                inObject = in.readObject();
                diff = System.currentTimeMillis();
                if (diff - start <= readWriteTimeout) {
                  LogUtil.info(
                      "Server : - failed to receive Request-Proposal from client.Timeout " + (diff
                          - start));
                  break;
                }
              }

              if (inObject instanceof Request) {
                Request secondRequest = (Request) inObject;
                List<Packet> clientPackets = new ArrayList<>();
                Response secondResponse = new Response(0, Status.OK);
                out.writeObject(secondResponse);
                out.flush();
                LogUtil.info("Server : From client received " + secondRequest);
                LogUtil.info("Server : To client sent " + secondResponse);
                int packetNr = secondRequest.getPackets();
                Outer:
                for (int k = 0; k < packetNr; k++) {
                  Object inPacket = in.readObject();
                  start = System.currentTimeMillis();
                  diff = start;
                  while (inPacket == null) {
                    inPacket = in.readObject();
                    diff = System.currentTimeMillis();
                    if (diff - start <= readWriteTimeout) {
                      LogUtil.info("Server : - " + k
                          + " failed to receive packet from client , client-response " + inPacket
                          + ".Receiving stopped.Timeout : " + (diff - start));
                      break Outer;
                    }
                  }
                  Packet packet = (Packet) inPacket;
                  clientPackets.add(packet);
                }
                out.writeObject(secondResponse);
                out.flush();
                LogUtil.info(
                    "Server : -  received from client , teilen-server-response " + secondResponse);
                PacketQueue.writeIn(clientPackets);
              }

            } catch (ClassNotFoundException e) {
              e.printStackTrace();
              removeClientsIndex.add(i);
            } catch (SocketException se) {
              se.printStackTrace();
              removeClientsIndex.add(i);

            } catch (NullPointerException npe) {
              npe.printStackTrace();
              removeClientsIndex.add(i);
            } catch (IOException io) {
              io.printStackTrace();
              removeClientsIndex.add(i);
            }

          }

          removeClientSockets(removeClientsIndex);
          LogUtil.info("IOE: finished.");
          Thread.sleep(threadSleep);
        }
        LogUtil.info("--> IOE: waiting.");
        Thread.sleep(threadSleep);
        if (discoveryEngine != null) {
          this.discoveryEngine.shutdown();
          this.discoveryEngine = null;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void startServer() throws IOException {
    if (!this.discoveryEngineFlag) {
      this.discoveryEngineFlag = true;
    }
  }

  public synchronized void shutdownServer() throws IOException {
    if (this.discoveryEngineFlag) {
      this.discoveryEngineFlag = false;
    }
  }

  class DiscoveryEngine extends Thread {

    private final int serverSocketPort = 8888;
    private final int serverSocketTimeout = 1500;
    private int counter = 0;
    private Boolean runFlag = null;
    private ServerSocket serverSocket;

    public DiscoveryEngine() {
      super("DiscoveryEngine");
    }

    @Override
    public void run() {
      try {
        this.serverSocket = new ServerSocket(serverSocketPort);
        this.serverSocket.setSoTimeout(serverSocketTimeout);
        this.runFlag = true;
        LogUtil.info("Socket teilen-server started.Port " + serverSocketPort + ", timeout "
            + serverSocketTimeout);
        while (runFlag) {
          try {
            Socket socket = serverSocket.accept();
            counter++;
            ClientSocket clientSocket = new ClientSocket(counter, socket);
            addClientSocket(clientSocket);

          } catch (SocketTimeoutException ste) {
            LogUtil.warn("Socket teilen-server timeout " + serverSocketTimeout + " reached.");
          } catch (SocketException se) {
            LogUtil.warn("Socket teilen-server closed.");
          } catch (Exception e) {
            e.printStackTrace();
          }
          Thread.sleep(4000);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public synchronized void shutdown() {
      try {
        runFlag = false;
        removeAllClientSockets();
        try {
          if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
            LogUtil.warn(serverSocket + " closed.");
          }
          Thread.currentThread().interrupt();
        } catch (Exception e) {
          e.printStackTrace();
        }

      } catch (Exception e) {
        LogUtil.error(e.getMessage());
      }
    }
  }


}
