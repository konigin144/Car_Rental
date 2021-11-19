package com.example.car_rental.services;

import com.example.car_rental.entities.Rent;
import com.example.car_rental.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rep;

    public Rent getRentById(Integer id) {
        return rep.findById(id).get();
    }

    public List<Rent> getAllRents() {
        return rep.findAll();
    }

    public boolean addRent (Rent rent) {
        try {
            rep.save(rent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteRent (Integer id) {
        try {
            rep.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateRent (Integer id, Rent rent) {
        if (rep.existsById(id)) {
            rep.findById(id).map(rent1 -> {
                rent1.setCar(rent.getCar());
                rent1.setClient(rent.getClient());
                return rep.save(rent1);
            }).get();
            return true;
        } else {
            return false;
        }
    }
}
