package com.altf4.app.model.application;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "mobile_number")
    private long mobileNumber;

    @Column(name = "email")
    private String email;

}