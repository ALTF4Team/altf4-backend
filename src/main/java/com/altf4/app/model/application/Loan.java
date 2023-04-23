package com.altf4.app.model.application;

import com.altf4.app.model.application.type.LoanPurpose;
import com.altf4.app.validator.LoanTermConstraint;
import com.altf4.app.validator.DownPaymentAndLoanConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "loan")
@Data
@DownPaymentAndLoanConstraint
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanPurpose loanPurpose;

    private int totalAmount;

    private int downPayment;

    @LoanTermConstraint
    private int termYears;

}