package com.altf4.AltF4Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstimatedMonthlyPaymentRequest {

    private int loanSize;

    private int downPayment;

    private int loanTermYears;

}
