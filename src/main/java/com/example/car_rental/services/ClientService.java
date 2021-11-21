package com.example.car_rental.services;

import com.example.car_rental.entities.Client;
import com.example.car_rental.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository rep;

    public Client getClientById(Integer id) {
        try {
            return rep.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Client> getAllClients() {
        return rep.findAll();
    }

    public boolean addClient (Client client) {
        try {
            rep.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteClient (Integer id) {
        try {
            rep.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateClient (Integer id, Client client) {
        if (rep.existsById(id)) {
            rep.findById(id).map(client1 -> {
                client1.setFirstname(client.getFirstname());
                client1.setLastname(client.getLastname());
                return rep.save(client1);
            }).get();
            return true;
        } else {
            return false;
        }
    }
}
