package com.altf4.app.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MonthlyPaymentFormResponse {

    private final int monthlyPaymentAmount;
    private final String interestRate;
    private final int loanAmount;
    private final int totalInterestAmount;
    private final int totalPaymentSum;

    private MonthlyPaymentFormResponse(MonthlyPaymentResponseBuilder builder) {
        this.monthlyPaymentAmount = builder.monthlyPaymentAmount;
        this.interestRate = builder.interestRate;
        this.loanAmount = builder.loanAmount;
        this.totalInterestAmount = builder.totalInterestAmount;
        this.totalPaymentSum = builder.totalPaymentSum;
    }
    @Component
    public static class MonthlyPaymentResponseBuilder{
        private int monthlyPaymentAmount;
        private String interestRate;
        private int loanAmount;
        private int totalInterestAmount;
        private int totalPaymentSum;

        public MonthlyPaymentResponseBuilder() {}

        public MonthlyPaymentResponseBuilder monthlyPaymentAmount(int monthlyPaymentAmount) {
            this.monthlyPaymentAmount = monthlyPaymentAmount;
            return this;
        }
        public MonthlyPaymentResponseBuilder interestRate(String interestRate) {
            this.interestRate = interestRate;
            return this;
        }
        public MonthlyPaymentResponseBuilder loanAmount(int loanAmount) {
            this.loanAmount = loanAmount;
            return this;
        }
        public MonthlyPaymentResponseBuilder totalInterestAmount(int totalInterestAmount) {
            this.totalInterestAmount = totalInterestAmount;
            return this;
        }
        public MonthlyPaymentResponseBuilder totalPaymentSum(int totalPaymentSum) {
            this.totalPaymentSum = totalPaymentSum;
            return this;
        }

        public MonthlyPaymentFormResponse build() {
            return new MonthlyPaymentFormResponse(this);
        }
    }

}
