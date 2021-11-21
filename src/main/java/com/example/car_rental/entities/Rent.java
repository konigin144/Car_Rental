package com.example.car_rental.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table (name = "Rents")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @NonNull
    private Integer car_id;
    @NonNull
    private Integer client_id;

    @Column (name = "rent_date")
    @CreationTimestamp
    private Date rentdate;

    @Column (name = "return_date")
    private Date returndate;
}
