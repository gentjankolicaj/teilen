package org.teilen.desktop.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWrapper {

  public Socket socket;
  public ObjectOutputStream out;
  public ObjectInputStream in;

  public SocketWrapper(Socket socket) throws IOException {
    this.socket = socket;
    this.out = new ObjectOutputStream(socket.getOutputStream());
    this.in = new ObjectInputStream(socket.getInputStream());
  }

  public Socket getSocket() {
    return socket;
  }

  public ObjectOutputStream getOut() {
    return out;
  }


  public ObjectInputStream getIn() {
    return in;
  }

  public void closeIO() {
    closeO();
    closeI();
  }

  private void closeO() {
    try {
      if (out != null) {
        out.close();
      }
      out = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void closeI() {
    try {
      if (in != null) {
        in.close();
      }
      in = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close() {
    closeIO();
    try {
      if (socket != null) {
        socket.close();
      }
      socket = null;
    } catch (IOException io) {
      io.printStackTrace();
    }
  }


}
