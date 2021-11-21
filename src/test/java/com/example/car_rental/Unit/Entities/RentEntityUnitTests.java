package com.example.car_rental.Unit.Entities;

import com.example.car_rental.entities.Rent;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentEntityUnitTests {
    Date date = new java.util.Date();
    private Rent rent = new Rent(1, 2, 3, date, date);

    @Test
    public void getId() {
        assertEquals(1, rent.getId());
    }

    @Test
    public void setId() {
        rent.setId(2);
        assertEquals(2, rent.getId());
    }

    @Test
    public void getCarId() {
        assertEquals(2, rent.getCar_id());
    }

    @Test
    public void setCarId() {
        rent.setCar_id(4);
        assertEquals(4, rent.getCar_id());
    }

    @Test
    public void getClientId() {
        assertEquals(3, rent.getClient_id());
    }

    @Test
    public void setClientId() {
        rent.setClient_id(4);
        assertEquals(4, rent.getClient_id());
    }

    @Test
    public void getRentDate() {
        assertEquals(date, rent.getRentdate());
    }

    @Test
    public void setRentDate() {
        Date date2 = new java.util.Date();
        rent.setRentdate(date2);
        assertEquals(date2, rent.getRentdate());
    }

    @Test
    public void getReturnDate() {
        assertEquals(date, rent.getReturndate());
    }

    @Test
    public void setReturnDate() {
        Date date2 = new java.util.Date();
        rent.setReturndate(date2);
        assertEquals(date2, rent.getReturndate());
    }
}
