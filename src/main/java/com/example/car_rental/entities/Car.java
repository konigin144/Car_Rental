package com.example.car_rental.entities;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table (name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    private String brand;
    private String model;
}
