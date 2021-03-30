package com.br.elton.maven.client.controller;

import com.br.elton.maven.client.dto.ClientDto;
import com.br.elton.maven.client.entities.Client;
import com.br.elton.maven.client.mapper.ClientMapper;
import com.br.elton.maven.client.response.Response;
import com.br.elton.maven.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<Response<ClientDto>> saveClient(@RequestBody ClientDto newClient) throws ParseException {

        Client client = clientMapper.convertClientDtoToClient(newClient);
        client = clientService.saveClient(client);

        Response<ClientDto> response = new Response<ClientDto>();
        response.setData(clientMapper.convertClientToClientDto(client));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ClientDto>> findClientById(@PathVariable("id") Long id) throws Exception {
        Optional<Client> client = clientService.findClientById(id);
        Response<ClientDto> response = new Response<ClientDto>();

        if (!client.isPresent()) {
            log.info("Product not found by ID: {}", id);
            response.getErrors().add("Client not found by ID " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(clientMapper.convertClientToClientDto(client.get()));
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Response<List<ClientDto>>> findClients() throws ParseException {
        List<Client> clients = clientService.findClients();
        List<ClientDto> listClientDto = clientMapper.convertListClientToClientDto(clients);

        Response<List<ClientDto>> response = new Response<List<ClientDto>>();
        response.setData(listClientDto);

        return ResponseEntity.ok(response);
    }

}
