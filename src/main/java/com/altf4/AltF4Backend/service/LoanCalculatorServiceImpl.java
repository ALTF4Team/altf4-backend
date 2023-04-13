package com.altf4.AltF4Backend.service;

import com.altf4.AltF4Backend.dto.LoanCalculationResponseDTO;
import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;
import com.altf4.AltF4Backend.model.BanksLoanTerms;
import com.altf4.AltF4Backend.validator.EstimatedMonthlyPaymentRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanCalculatorServiceImpl implements LoanCalculatorService {

    @Autowired
    private LoanCalculationResponseDTO loanCalculationResponseDTO;
    @Autowired
    private EstimatedMonthlyPaymentRequestValidator validator;

    private final BanksLoanTerms banksLoanTerms = new BanksLoanTerms();


    @Override
    public LoanCalculationResponseDTO calculateMonthlyAnnuityPayments(
            EstimatedMonthlyPaymentRequest estimatedMonthlyPaymentRequest) {

        validator.validate(estimatedMonthlyPaymentRequest);

        int banksLoanAmount = estimatedMonthlyPaymentRequest.getLoanSize() - estimatedMonthlyPaymentRequest.getDownPayment();
        double monthlyInterestRate = (banksLoanTerms.getEuriborRate() + banksLoanTerms.getMargin()) / 12;
        int loanTermMonths = estimatedMonthlyPaymentRequest.getLoanTermYears() * 12;

        int equatedMonthlyInstallment = (int) (Math.round(
                banksLoanAmount * monthlyInterestRate
                * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                / (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1) / 10.0) * 10);

        loanCalculationResponseDTO.setMonthlyPaymentAmount(equatedMonthlyInstallment);

        return loanCalculationResponseDTO;
    }


}
