package com.example.car_rental.entities;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Table (name = "Rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    Car car;

    @ManyToOne
    Client client;
}
