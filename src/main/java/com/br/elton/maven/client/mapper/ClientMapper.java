package com.br.elton.maven.client.mapper;

import com.br.elton.maven.client.dto.ClientDto;
import com.br.elton.maven.client.entities.Client;
import com.br.elton.maven.client.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientMapper {

    @Autowired
    private Utils utils;

    public ClientDto convertClientToClientDto(Client client) throws ParseException {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setBirthDate(utils.convertLocalDateToString(client.getBirthDate()));
        clientDto.setCreateDate(utils.convertLocalDateToString(client.getCreateDate()));
        clientDto.setIdentificationDocument(client.getIdentificationDocument());

        return clientDto;
    }

    public Client convertClientDtoToClient(ClientDto clientDto) throws ParseException {
        Client client = new Client();
        client.setId((clientDto.getId() == null ? 0L : clientDto.getId()));
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setBirthDate(utils.convertStringToLocalDate(clientDto.getBirthDate()));
        client.setCreateDate((clientDto.getCreateDate() == null ? LocalDate.now() : utils.convertStringToLocalDate(clientDto.getCreateDate())));
        client.setIdentificationDocument(clientDto.getIdentificationDocument());

        return client;
    }

    public List<ClientDto> convertListClientToClientDto(List<Client> clients) throws ParseException {
        List<ClientDto> listClientDto = new ArrayList<ClientDto>();
        for (Client client : clients) {
            ClientDto findClientDto = convertClientToClientDto(client);
            listClientDto.add(findClientDto);
        }

        return listClientDto;
        //return clients.stream().filter(client -> convertClientToClientDto(client)).collect(Collectors.toList());
        //convertListClientToClientDto().isEmpty()
    }

}
