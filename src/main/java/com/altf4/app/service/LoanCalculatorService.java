package com.altf4.app.service;

import com.altf4.app.model.calculation.CallToApi;
import com.altf4.app.model.calculation.LoanCalculationRequest;
import com.altf4.app.model.calculation.LoanCalculationResponse;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.calculation.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {

    static CallToApi callToApi = new CallToApi();

    private static int calculateMonthlyPayment(LoanCalculationRequest request) {
        int banksLoanAmount = calculateLoanAmount(request);
        double monthlyInterestRate = (callToApi.euriborRate() + MARGIN) / 12;
        int loanTermMonths = request.getTermYears() * 12;

        return (int) (Math.round(banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);
    }

    private static LoanCalculationResponse buildResponse(LoanCalculationRequest request, int monthlyPayment) {
        return new LoanCalculationResponse.LoanCalculationResponseBuilder()
                .monthlyPaymentAmount(monthlyPayment)
                .interestRate(callToApi.euriborRate() + MARGIN)
                .loanAmount(calculateLoanAmount(request))
                .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                .build();
    }

    private static int calculateLoanAmount(LoanCalculationRequest request) {
        return request.getTotalAmount() - request.getDownPayment();
    }

    private static int calculateTotalInterestAmount(LoanCalculationRequest request, int monthlyPayment) {
        return monthlyPayment * 12 * request.getTermYears() - (request.getTotalAmount() - request.getDownPayment());
    }

    private static int calculateTotalPaymentSum(LoanCalculationRequest request, int monthlyPayment) {
        return calculateLoanAmount(request) + calculateTotalInterestAmount(request, monthlyPayment);
    }

    public LoanCalculationResponse getLoanCalculations(LoanCalculationRequest request) {

        int monthlyPayment = calculateMonthlyPayment(request);
        return buildResponse(request, monthlyPayment);
    }

}