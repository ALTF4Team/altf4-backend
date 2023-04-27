package com.altf4.app.service;

import com.altf4.app.model.calculation.ApiErrorException;
import com.altf4.app.model.calculation.ApiNinjas;
import com.altf4.app.model.calculation.LoanCalculationRequest;
import com.altf4.app.model.calculation.LoanCalculationResponse;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.calculation.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {

    static ApiNinjas callToApi = new ApiNinjas();
    static double defaultEuribor = 0.03335;
    private static int calculateMonthlyPayment(LoanCalculationRequest request) {
        int banksLoanAmount = calculateLoanAmount(request);
        double monthlyInterestRate = 0;
        try {
            monthlyInterestRate = (callToApi.getEuriborRateFor6Months() + MARGIN) / 12;
        } catch (ApiErrorException e) {
            monthlyInterestRate = (defaultEuribor+ MARGIN) / 12;
        }
        int loanTermMonths = request.getTermYears() * 12;

        return (int) (Math.round(banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);
    }

    private static LoanCalculationResponse buildResponse(LoanCalculationRequest request, int monthlyPayment) {
        try {
            return new LoanCalculationResponse.LoanCalculationResponseBuilder()
                    .monthlyPaymentAmount(monthlyPayment)
                    .interestRate(callToApi.getEuriborRateFor6Months() + MARGIN)
                    .loanAmount(calculateLoanAmount(request))
                    .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                    .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                    .build();
        } catch (ApiErrorException e) {
            return new LoanCalculationResponse.LoanCalculationResponseBuilder()
                    .monthlyPaymentAmount(monthlyPayment)
                    .interestRate(defaultEuribor + MARGIN)
                    .loanAmount(calculateLoanAmount(request))
                    .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                    .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                    .build();
        }
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