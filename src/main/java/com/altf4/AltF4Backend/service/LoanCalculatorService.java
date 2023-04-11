package com.altf4.AltF4Backend.service;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;

public interface LoanCalculatorService {

    LoanCalculationResponse calculateAvailableLoanDetails();
}
