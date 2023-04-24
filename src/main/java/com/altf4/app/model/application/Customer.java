package com.altf4.app.model.application;

import com.altf4.app.validator.AgeConstraint;
import com.altf4.app.validator.CountryOfCitizenshipConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String surname;

    @CountryOfCitizenshipConstraint
    @Column(name = "citizenship")
    private String countryOfCitizenship;

    @AgeConstraint
    @Past
    private LocalDate birthDate;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String mobileNumber;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

}