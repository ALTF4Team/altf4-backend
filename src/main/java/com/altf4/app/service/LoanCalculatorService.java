package com.altf4.app.service;

import com.altf4.app.model.monthlypayments.ApiErrorException;
import com.altf4.app.model.monthlypayments.ApiNinjas;
import com.altf4.app.model.monthlypayments.MonthlyPaymentsRequest;
import com.altf4.app.model.monthlypayments.MonthlyPaymentsResponse;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.monthlypayments.LoanTerms.DEFAULT_EURIBOR;
import static com.altf4.app.model.monthlypayments.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {

    static ApiNinjas callToApi = new ApiNinjas();

    public MonthlyPaymentsResponse getLoanCalculations(MonthlyPaymentsRequest request) {

        int monthlyPayment = calculateMonthlyPayment(request);
        return buildResponse(request, monthlyPayment);
    }

    private static int calculateMonthlyPayment(MonthlyPaymentsRequest request) {
        int banksLoanAmount = calculateLoanAmount(request);
        int loanTermMonths = request.getTermYears() * 12;

        double monthlyInterestRate;
        try {
            monthlyInterestRate = (callToApi.getEuriborRateFor6Months() + MARGIN) / 12;
        } catch (ApiErrorException e) {
            monthlyInterestRate = (DEFAULT_EURIBOR + MARGIN) / 12;
        }

        return (int) (Math.round(banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);
    }

    private static MonthlyPaymentsResponse buildResponse(MonthlyPaymentsRequest request, int monthlyPayment) {
        try {
            return new MonthlyPaymentsResponse.LoanCalculationResponseBuilder()
                    .monthlyPaymentAmount(monthlyPayment)
                    .interestRate(callToApi.getEuriborRateFor6Months() + MARGIN)
                    .loanAmount(calculateLoanAmount(request))
                    .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                    .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                    .build();
        } catch (ApiErrorException e) {
            return new MonthlyPaymentsResponse.LoanCalculationResponseBuilder()
                    .monthlyPaymentAmount(monthlyPayment)
                    .interestRate(DEFAULT_EURIBOR + MARGIN)
                    .loanAmount(calculateLoanAmount(request))
                    .totalInterestAmount(calculateTotalInterestAmount(request, monthlyPayment))
                    .totalPaymentSum(calculateTotalPaymentSum(request, monthlyPayment))
                    .build();
        }
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