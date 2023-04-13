package com.altf4.AltF4Backend.service;

import com.altf4.AltF4Backend.dto.LoanCalculationResponseDTO;
import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;


public interface LoanCalculatorService {

    LoanCalculationResponseDTO calculateMonthlyAnnuityPayments(EstimatedMonthlyPaymentRequest estimatedMonthlyPaymentRequest);
}
