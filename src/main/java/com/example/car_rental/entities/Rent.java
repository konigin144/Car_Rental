package com.example.car_rental.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table (name = "Rents")
@RequiredArgsConstructor
@AllArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    private Integer car_id;
    private Integer client_id;

    @Column (name = "rent_date")
    @CreationTimestamp
    private Date rentdate;

    @Column (name = "return_date")
    private Date returndate;

    public Rent (Integer car_id, Integer client_id) {
        this.car_id = car_id;
        this.client_id = client_id;
    }
}
