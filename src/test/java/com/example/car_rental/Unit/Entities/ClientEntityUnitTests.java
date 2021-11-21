package com.example.car_rental.Unit.Entities;

import com.example.car_rental.entities.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientEntityUnitTests {
    private Client client = new Client(1, "Anna", "Nowak");

    @Test
    public void getId() {
        assertEquals(1, client.getId());
    }

    @Test
    public void setId() {
        client.setId(2);
        assertEquals(2, client.getId());
    }

    @Test
    public void getFirstname() {
        assertEquals("Anna", client.getFirstname());
    }

    @Test
    public void setFirstname() {
        client.setFirstname("Aleksandra");
        assertEquals("Aleksandra", client.getFirstname());
    }

    @Test
    public void getLastname() {
        assertEquals("Nowak", client.getLastname());
    }

    @Test
    public void setLastname() {
        client.setLastname("Kowalska");
        assertEquals("Kowalska", client.getLastname());
    }
}
