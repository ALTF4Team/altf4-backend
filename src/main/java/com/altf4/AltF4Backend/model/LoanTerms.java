package com.altf4.AltF4Backend.model;

import lombok.Data;

@Data
public class LoanTerms {

    private double euriborRate;

    private double margin;

    private double interestRate;

    private double loanServiceRatio;

    private double annuityPaymentAmount;

    private double linearPaymentAmount;


}
