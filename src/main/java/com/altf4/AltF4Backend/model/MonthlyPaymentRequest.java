package com.altf4.AltF4Backend.model;

import lombok.Data;

@Data
public class MonthlyPaymentRequest {

    private double loanSize;

    private double downPayment;

    private int loanTermYears;

}
