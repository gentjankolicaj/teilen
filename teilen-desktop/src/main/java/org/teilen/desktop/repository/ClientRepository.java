package org.teilen.desktop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.teilen.common.domain.Client;

public class ClientRepository {

  static final Map<Integer, Client> clients = new HashMap<>();
  static Client owner = null;

  public static Client findOwner() {
    return owner;
  }

  public static Integer findOwnerId() {
    if (owner != null) {
      return owner.getId();
    } else {
      return null;
    }
  }

  public static void insertOwner(Client client) {
    owner = client;
  }

  public static void updateOwner(String firstname, String lastname) {
    if (owner != null) {
      owner.setFirstname(firstname);
      owner.setLastname(lastname);
    }
  }

  public static void deleteOwner() {
    owner = null;
  }


  //clients crud methods
  public static Map<Integer, Client> findAll() {
    return clients;
  }

  public static List<Client> findAllList() {
    return clients.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
  }

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

  public static void deleteClient(Integer clientId) {
    clients.remove(clientId);
  }

  public static void deleteAll() {
    clients.clear();
  }

}
