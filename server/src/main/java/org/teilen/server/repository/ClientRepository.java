package org.teilen.server.repository;

import org.teilen.common.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientRepository {

    static final Map<Integer, Client> clients = new HashMap<>();

    public static Client findClientById(Integer clientId) {
        return clients.get(clientId);
    }

    public static void insertClient(Client client) {
        clients.put(client.getId(), client);
    }

    public static void updateClient(Client client) {
        Client actual = clients.get(client.getId());
        if (actual != null) {
            clients.replace(client.getId(), actual, client);
        } else {
            clients.put(client.getId(), client);
        }
    }

    public static void deleteClient(Client client) {
        clients.remove(client.getId());
    }

}
