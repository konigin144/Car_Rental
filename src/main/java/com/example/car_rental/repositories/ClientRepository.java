package com.example.car_rental.repositories;
import com.example.car_rental.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
//    Client getClientById (Integer id);
//    List<Client> getAllClients();
//    boolean addClient (Client client);
//    boolean deleteClient (Client client);
}
