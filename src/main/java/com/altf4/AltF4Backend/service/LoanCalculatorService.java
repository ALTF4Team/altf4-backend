package com.altf4.AltF4Backend.service;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;
import com.altf4.AltF4Backend.model.MonthlyPaymentRequest;
import com.altf4.AltF4Backend.model.BanksLoanTerms;

public interface LoanCalculatorService {

    LoanCalculationResponse calculateAvailableLoanDetails(MonthlyPaymentRequest loanRequest, BanksLoanTerms loanTerms);
}
