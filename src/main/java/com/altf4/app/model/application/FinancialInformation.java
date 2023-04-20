package com.altf4.app.model.application;

import com.altf4.app.model.application.type.Education;
import com.altf4.app.model.application.type.EmploymentContractType;
import com.altf4.app.model.application.type.EmploymentStatus;
import com.altf4.app.model.application.type.MaritalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "financial_information")
@Data
public class FinancialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotBlank
    private String sourceOfIncome;

    @Min(0)
    @Max(100)
    @Column(name = "years_self_employed")
    private double yearsSelfEmployment;

    @NotBlank
    private String currentEmployer;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "years_with_current_employer")
    private double yearsCurrentEmployer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentContractType employmentContractType;

    @NotNull
    private String position;

    @NotNull
    private String industry;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Education education;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Min(0)
    @Max(10)
    @Column(name = "number_of_underage_dependents")
    private int underageDependentsCount;

    @PositiveOrZero
    private int monthlyIncome;

    private boolean coBorrower;

}