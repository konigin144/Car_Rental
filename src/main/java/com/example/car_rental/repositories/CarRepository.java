package com.example.car_rental.repositories;
import com.example.car_rental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {


//    Car getCarById (Integer id);
//    List<Car> getAllCars();
//    List<Car> getCarsByBrand(String brand);
//    boolean addCar (Car car);
//    boolean deleteCar (Car car);
}
