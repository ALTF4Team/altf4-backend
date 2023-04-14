package com.altf4.app.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class LoanCalculationResponse {

    private final int monthlyPaymentAmount;
    private final double interestRate;
    private final int loanAmount;
    private final int totalInterestAmount;
    private final int totalPaymentSum;

    private LoanCalculationResponse(LoanCalculationResponseBuilder builder) {
        this.monthlyPaymentAmount = builder.monthlyPaymentAmount;
        this.interestRate = builder.interestRate;
        this.loanAmount = builder.loanAmount;
        this.totalInterestAmount = builder.totalInterestAmount;
        this.totalPaymentSum = builder.totalPaymentSum;
    }
    @Component
    public static class LoanCalculationResponseBuilder {
        private int monthlyPaymentAmount;
        private double interestRate;
        private int loanAmount;
        private int totalInterestAmount;
        private int totalPaymentSum;

        public LoanCalculationResponseBuilder() {}

        public LoanCalculationResponseBuilder monthlyPaymentAmount(int monthlyPaymentAmount) {
            this.monthlyPaymentAmount = monthlyPaymentAmount;
            return this;
        }
        public LoanCalculationResponseBuilder interestRate(double interestRate) {
            this.interestRate = interestRate;
            return this;
        }
        public LoanCalculationResponseBuilder loanAmount(int loanAmount) {
            this.loanAmount = loanAmount;
            return this;
        }
        public LoanCalculationResponseBuilder totalInterestAmount(int totalInterestAmount) {
            this.totalInterestAmount = totalInterestAmount;
            return this;
        }
        public LoanCalculationResponseBuilder totalPaymentSum(int totalPaymentSum) {
            this.totalPaymentSum = totalPaymentSum;
            return this;
        }

        public LoanCalculationResponse build() {
            return new LoanCalculationResponse(this);
        }
    }
}
