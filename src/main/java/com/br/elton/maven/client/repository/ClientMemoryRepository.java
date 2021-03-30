package com.br.elton.maven.client.repository;

import com.br.elton.maven.client.entities.Client;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientMemoryRepository {

    List<Client> listClient;
    Long incrementar = 1L;

    public Client saveClient(Client client) {

        client.setId(incrementar);
        client.setCreateDate(LocalDate.now());

        if (listClient == null || listClient.size() == 0) {
            listClient = new ArrayList<Client>();
            listClient.add(client);
        } else {
            listClient.add(client);
        }
        incrementar++;

        return client;
    }

    public Optional<Client> findClientById(Long idClient) {
        if (listClient == null || listClient.size() == 0) {
            return Optional.empty();
        }

        return listClient.stream().filter(cli -> cli.getId().equals(idClient)).findFirst();
    }

    public List<Client> findClients() {
        if (listClient == null || listClient.size() == 0) {
            return listClient = new ArrayList<Client>();
        }
        return listClient;
    }

}
