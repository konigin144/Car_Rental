package com.example.car_rental.entities;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table (name = "Cars")
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    private String brand;
    private String model;
    private Boolean available;

    public Car(String brand, String model, Boolean available) {
        this.brand = brand;
        this.model = model;
        this.available = available;
    }
}
