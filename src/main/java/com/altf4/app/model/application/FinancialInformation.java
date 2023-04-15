package com.altf4.app.model.application;

import javax.persistence.*;

@Entity
@Table(name = "financial_information")
public class FinancialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "employment_status")
    private String employmentStatus;

    @Column(name = "source_of_income")
    private String sourceOfIncome;

    @Column(name = "years_self_employed")
    private byte yearsSelfEmployed;

    @Column(name = "current_employer")
    private String currentEmployer;

    @Column(name = "years_with_current_employer")
    private byte yearsWithCurrentEmployer;

    @Column(name = "employment_contract_type")
    private String employmentContractType;

    @Column(name = "position")
    private String position;

    @Column(name = "industry")
    private String industry;

    @Column(name = "education")
    private String education;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "number_of_underage_dependents")
    private byte numberOfUnderageDependents;

    @Column(name = "monthly_income")
    private int monthlyIncome;

    @Column(name = "co_borrower")
    private Customer coBorrower;

}