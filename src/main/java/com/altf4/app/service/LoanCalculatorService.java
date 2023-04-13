package com.altf4.app.service;

import com.altf4.app.model.MonthlyPaymentResponse;
import com.altf4.app.model.MonthlyPaymentRequest;
import com.altf4.app.validator.EstimatedMonthlyPaymentRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.LoanTerms.EURIBOR_RATE;
import static com.altf4.app.model.LoanTerms.MARGIN;

@Service
public class LoanCalculatorService {

    @Autowired
    private MonthlyPaymentResponse monthlyPaymentResponse;
    @Autowired
    private EstimatedMonthlyPaymentRequestValidator validator;


    public MonthlyPaymentResponse calculateMonthlyAnnuityPayments(
            MonthlyPaymentRequest monthlyPaymentRequest) {

        validator.validate(monthlyPaymentRequest);

        int banksLoanAmount = monthlyPaymentRequest.getLoanSize() - monthlyPaymentRequest.getDownPayment();
        double monthlyInterestRate = (EURIBOR_RATE + MARGIN) / 12;
        int loanTermMonths = monthlyPaymentRequest.getLoanTermYears() * 12;

        int equatedMonthlyInstallment = (int) (Math.round(
                banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);

        monthlyPaymentResponse.setMonthlyPaymentAmount(equatedMonthlyInstallment);

        return monthlyPaymentResponse;
    }


}
