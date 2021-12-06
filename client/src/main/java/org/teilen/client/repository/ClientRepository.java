package org.teilen.client.repository;

import org.teilen.common.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientRepository {

    static final Map<Integer, Client> clients = new HashMap<>();

    static Client findClientById(Integer clientId) {
        return clients.get(clientId);
    }

    static void insertClient(Client client) {
        clients.put(client.getId(), client);
    }

    static void updateClient(Client client) {
        Client actual = clients.get(client.getId());
        if (actual != null) {
            clients.replace(client.getId(), actual, client);
        } else {
            clients.put(client.getId(), client);
        }
    }

    static void deleteClient(Client client) {
        clients.remove(client.getId());
    }

}
