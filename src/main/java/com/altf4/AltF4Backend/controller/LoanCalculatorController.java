package com.altf4.AltF4Backend.controller;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;
import com.altf4.AltF4Backend.model.MonthlyPaymentRequest;
import com.altf4.AltF4Backend.model.BanksLoanTerms;
import com.altf4.AltF4Backend.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class LoanCalculatorController {

    private final LoanCalculatorService loanCalculatorService;
    private final BanksLoanTerms loanTerms;

    @PostMapping
    @ResponseBody
    public LoanCalculationResponse postLoanCalculations(@RequestBody MonthlyPaymentRequest loanRequest){
        return loanCalculatorService.calculateAvailableLoanDetails(loanRequest, loanTerms);
    }
}
