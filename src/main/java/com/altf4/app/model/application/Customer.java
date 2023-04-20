package com.altf4.app.model.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", table = "customer")
    @JsonIgnore
    private long id;

    @NotBlank(message = "/////////////////////////////////////////////")
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    @Column(name = "citizenship")
    private String countryOfCitizenship;

    @NotNull @Past
    @Column(name = "birth_date")
    private LocalDate yearOfBirth;

    @Size(min = 8, max = 20)
    private String mobileNumber;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

}