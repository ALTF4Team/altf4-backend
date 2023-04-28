package com.altf4.app.service;

import com.altf4.app.model.monthlypayments.MonthlyPaymentsRequest;
import com.altf4.app.model.monthlypayments.MonthlyPaymentsResponse;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.monthlypayments.LoanTerms.EURIBOR_RATE;
import static com.altf4.app.model.monthlypayments.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {


    public MonthlyPaymentsResponse getLoanCalculations(MonthlyPaymentsRequest request) {

        int monthlyPayment = calculateMonthlyPayment(request);
        return buildResponse(request, monthlyPayment);
    }

    private static int calculateMonthlyPayment(MonthlyPaymentsRequest request) {
        int banksLoanAmount = calculateLoanAmount(request);
        double monthlyInterestRate = (EURIBOR_RATE + MARGIN) / 12;
        int loanTermMonths = request.getTermYears() * 12;

        return (int) (Math.round(banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);
    }

    private static MonthlyPaymentsResponse buildResponse(MonthlyPaymentsRequest request, int monthlyPayment) {
        return new MonthlyPaymentsResponse.LoanCalculationResponseBuilder()
                .monthlyPaymentAmount(monthlyPayment)
                .interestRate(EURIBOR_RATE + MARGIN)
                .loanAmount(calculateLoanAmount(request))
                .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                .build();
    }

    private static int calculateLoanAmount(MonthlyPaymentsRequest request) {
        return request.getTotalAmount() - request.getDownPayment();
    }

    private static int calculateTotalInterestAmount(MonthlyPaymentsRequest request, int monthlyPayment) {
        return monthlyPayment * 12 * request.getTermYears() - (request.getTotalAmount() - request.getDownPayment());
    }

    private static int calculateTotalPaymentSum(MonthlyPaymentsRequest request, int monthlyPayment) {
        return calculateLoanAmount(request) + calculateTotalInterestAmount(request, monthlyPayment);
    }

}