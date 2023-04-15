package com.altf4.app.model.application;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "loan")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(table = "id")
    private int id;

    @Column(table = "loan_purpose")
    private String loanPurpose;

    @Column(table = "total_amount")
    private int totalAmount;

    @Column(table = "down_payment")
    private int downPayment;

    @Column(table = "term_years")
    private byte termYears;

}