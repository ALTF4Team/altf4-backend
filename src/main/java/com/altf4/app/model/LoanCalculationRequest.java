package com.altf4.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanCalculationRequest {

    private int totalAmount;

    private int downPayment;

    private int termYears;

}