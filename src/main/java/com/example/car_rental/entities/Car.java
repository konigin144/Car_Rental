package com.example.car_rental.entities;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;

@Data
@Entity
@Table (name = "Cars")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @NonNull
    private String brand;
    @NonNull
    private String model;
    @NonNull
    private Boolean available;
}
