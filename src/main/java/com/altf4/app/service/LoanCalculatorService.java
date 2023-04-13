package com.altf4.app.service;

import com.altf4.app.model.MonthlyPaymentResponse;
import com.altf4.app.model.MonthlyPaymentRequest;


public interface LoanCalculatorService {

    MonthlyPaymentResponse calculateMonthlyAnnuityPayments(MonthlyPaymentRequest monthlyPaymentRequest);
}
