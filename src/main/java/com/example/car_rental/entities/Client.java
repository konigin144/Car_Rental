package com.example.car_rental.entities;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table (name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @Column (name = "first_name")
    private String firstname;
    @Column (name = "last_name")
    private String lastname;
}
