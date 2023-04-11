package com.altf4.AltF4Backend.model;

import lombok.Data;

@Data
public class LoanRequest {

    private double loanSize;

    private double downPayment;

    private double valueOfAsset;

    private int loanTermYears;

    private int clientsSalary;

    
}
