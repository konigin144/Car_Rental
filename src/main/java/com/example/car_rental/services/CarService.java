package com.example.car_rental.services;

import com.example.car_rental.entities.Car;
import com.example.car_rental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository rep;

    public Car getCarById(Integer id) {
        try {
            return rep.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Car> getAllCars() {
        return rep.findAll();
    }

    public boolean addCar (Car car) {
        try {
            rep.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCar (Integer id, Car car) {
        if (rep.existsById(id)) {
            rep.findById(id).map(car1 -> {
                car1.setBrand(car.getBrand());
                car1.setModel(car.getModel());
                car1.setAvailable(car.getAvailable());
                return rep.save(car1);
            }).get();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCar (Integer id) {
        try {
            rep.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
