package com.example.car_rental.controller;

import com.example.car_rental.entities.Client;
import com.example.car_rental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/client", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping(value = "/{id}")
    public Client getClientById(@PathVariable Integer id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return client;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @PostMapping(value = "")
    public void addClient(@RequestBody Client client) {
        if (clientService.addClient(client)) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Client created");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @PutMapping(value = "/{id}")
    public void putClient(@RequestBody Client client, @PathVariable Integer id) {
        if (clientService.updateClient(id, client)) {
            throw new ResponseStatusException(HttpStatus.OK, "Client updated");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable Integer id) {
        if (clientService.deleteClient(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "Client deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }
}
