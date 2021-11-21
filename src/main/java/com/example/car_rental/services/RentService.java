package com.example.car_rental.services;

import com.example.car_rental.entities.Car;
import com.example.car_rental.entities.Rent;
import com.example.car_rental.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rep;

    @Autowired
    private CarRepository carrep;

    @Autowired
    private ClientRepository clientrep;

    public Rent getRentById(Integer id) {
        try {
            return rep.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Rent> getAllRents() {
        return rep.findAll();
    }

    public boolean addRent (Rent rent) {
        if (carrep.existsById(rent.getCar_id()) && clientrep.existsById(rent.getClient_id())) {
            if (carrep.getById(rent.getCar_id()).getAvailable()) {
                try {
                    Car car = carrep.getById(rent.getCar_id());
                    carrep.findById(rent.getCar_id()).map(car1 -> {
                        car1.setAvailable(false);
                        car1.setBrand(car.getBrand());
                        car1.setModel(car.getModel());
                        return carrep.save(car1);
                    }).get();
                    rep.save(rent);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
        }
        } else {
            return false;
        }
    }

    public boolean returnRent (Integer id) {
        if (rep.existsById(id) && rep.findById(id).get().getReturndate() == null) {
            rep.findById(id).map(rent1 -> {
                rent1.setReturndate(new java.util.Date());
                Car car = carrep.getById(rent1.getCar_id());
                carrep.findById(rent1.getCar_id()).map(car1 -> {
                    car1.setAvailable(true);
                    car1.setBrand(car.getBrand());
                    car1.setModel(car.getModel());
                    return carrep.save(car1);
                }).get();
                return rep.save(rent1);
            }).get();
            return true;
        } else {
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
                rent1.setCar_id(rent.getCar_id());
                rent1.setClient_id(rent.getClient_id());
                return rep.save(rent1);
            }).get();
            return true;
        } else {
            return false;
        }
    }
}
