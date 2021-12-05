package org.teilen.server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
public class ClientSocket {
    private Integer clientId;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientSocket(Integer clientId, Socket socket) throws IOException {
        this.clientId = clientId;
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getOut() throws IOException {
        return out;
    }

    public ObjectInputStream getIn() throws IOException {
        return in;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void closeIO() {
        closeO();
        closeI();
    }

    private void closeO() {
        try {
            if (out != null)
                out.close();
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeI() {
        try {
            if (in != null)
                in.close();
            in = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        closeIO();
        try {
            if (socket != null)
                socket.close();
            socket = null;
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSocket that = (ClientSocket) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(socket, that.socket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, socket);
    }

    @Override
    public String toString() {
        return "ClientSocket{" +
                "clientId=" + clientId +
                ", socket=" + socket +
                '}';
    }
}