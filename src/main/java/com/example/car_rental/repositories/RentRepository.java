package com.example.car_rental.repositories;
import com.example.car_rental.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
//    Rent getRentById (Integer id);
//    List<Rent> getAllRents();
//    boolean addRent (Rent rent);
//    boolean deleteRent (Rent rent);
//    Rent rentCar (Car car, Client client);
//    Rent returnCar (Rent id);
}
