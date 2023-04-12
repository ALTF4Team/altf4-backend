package com.altf4.AltF4Backend.service;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;
import com.altf4.AltF4Backend.model.LoanRequest;
import com.altf4.AltF4Backend.model.LoanTerms;

public interface LoanCalculatorService {

    LoanCalculationResponse calculateAvailableLoanDetails(LoanRequest loanRequest, LoanTerms loanTerms);
}
