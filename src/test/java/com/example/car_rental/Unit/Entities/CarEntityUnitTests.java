package com.example.car_rental.Unit.Entities;

import com.example.car_rental.entities.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarEntityUnitTests {
    private Car car = new Car(1,"Toyota","Yaris", true);

    @Test
    public void getId() {
        assertEquals(1, car.getId());
    }

    @Test
    public void setId() {
        car.setId(2);
        assertEquals(2, car.getId());
    }

    @Test
    public void getBrand() {
        assertEquals("Toyota", car.getBrand());
    }

    @Test
    public void setBrand() {
        car.setBrand("Suzuki");
        assertEquals("Suzuki", car.getBrand());
    }

    @Test
    public void getModel() {
        assertEquals("Yaris", car.getModel());
    }

    @Test
    public void setModel() {
        car.setModel("Swift");
        assertEquals("Swift", car.getModel());
    }

    @Test
    public void getAvailable() {
        assertEquals(true, car.getAvailable());
    }

    @Test
    public void setAvailable() {
        car.setAvailable(false);
        assertEquals(false, car.getAvailable());
    }
}
