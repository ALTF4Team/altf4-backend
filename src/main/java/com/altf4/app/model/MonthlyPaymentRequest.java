package com.altf4.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyPaymentRequest {

    private int loanSize;

    private int downPayment;

    private int loanTermYears;

}
