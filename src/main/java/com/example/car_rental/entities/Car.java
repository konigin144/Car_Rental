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

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
