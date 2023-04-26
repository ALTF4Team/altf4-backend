package com.altf4.app.model.application;

import com.altf4.app.model.application.type.Education;
import com.altf4.app.model.application.type.EmploymentContractType;
import com.altf4.app.model.application.type.EmploymentStatus;
import com.altf4.app.model.application.type.MaritalStatus;
import com.altf4.app.validator.FinancialInformationConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "financial_information")
@Data
@FinancialInformationConstraint
public class FinancialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String currentEmployer;

    @Column(name = "years_with_current_employer")
    private Double yearsCurrentEmployer;

    @Enumerated(EnumType.STRING)
    private EmploymentContractType employmentContractType;

    private String position;

    private String industry;

    @Column(name = "years_self_employed")
    private Double yearsSelfEmployment;

    private String sourceOfIncome;

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

    private String coBorrower;

}