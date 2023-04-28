package com.altf4.app.model.maxLoan;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MaxLoanResponse {
    private int maxLoanAmount;
    private int maxMonthlyPayment;

    public MaxLoanResponse() {
    }

    public MaxLoanResponse(int maxLoanAmount, int maxMonthlyPayment) {
        this.maxLoanAmount = maxLoanAmount;
        this.maxMonthlyPayment = maxMonthlyPayment;
    }

}
