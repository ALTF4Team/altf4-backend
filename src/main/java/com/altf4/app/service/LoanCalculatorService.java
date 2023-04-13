package com.altf4.app.service;

import com.altf4.app.model.MonthlyPaymentFormResponse;
import com.altf4.app.model.MonthlyPaymentFormRequest;
import com.altf4.app.validator.MonthlyPaymentRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.LoanTerms.EURIBOR_RATE;
import static com.altf4.app.model.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {

    private final MonthlyPaymentRequestValidator validator;

    @Autowired
    public LoanCalculatorService(MonthlyPaymentRequestValidator validator) {
        this.validator = validator;
    }


    public MonthlyPaymentFormResponse generateMonthlyPaymentFormResponse(MonthlyPaymentFormRequest request) {

        validator.validate(request);

        int monthlyPayment = calculateMonthlyPayment(request);

        return buildResponseObject(monthlyPayment, request);
    }


    private static int calculateMonthlyPayment(MonthlyPaymentFormRequest request) {
        int banksLoanAmount = calculateLoanAmount(request);
        double monthlyInterestRate = (EURIBOR_RATE + MARGIN) / 12;
        int loanTermMonths = request.getTermYears() * 12;

        return (int) (Math.round(banksLoanAmount * monthlyInterestRate
                     * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                     / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);
    }

    public MonthlyPaymentFormResponse buildResponseObject(int monthlyPayment, MonthlyPaymentFormRequest request) {
        return new MonthlyPaymentFormResponse.MonthlyPaymentResponseBuilder()
                .monthlyPaymentAmount(monthlyPayment)
                .interestRate(EURIBOR_RATE + MARGIN)
                .loanAmount(calculateLoanAmount(request))
                .totalInterestAmount(calculateTotalInterestAmount(monthlyPayment, request))
                .totalPaymentSum(calculateLoanAmount(request) + calculateTotalInterestAmount(monthlyPayment, request))
                .build();

    }

    private static int calculateLoanAmount(MonthlyPaymentFormRequest request) {
        return request.getTotalAmount() - request.getDownPayment();
    }
    private static int calculateTotalInterestAmount(int monthlyPayment, MonthlyPaymentFormRequest request) {
        return monthlyPayment * 12 * request.getTermYears() - (request.getTotalAmount() - request.getDownPayment());
    }

    


}
