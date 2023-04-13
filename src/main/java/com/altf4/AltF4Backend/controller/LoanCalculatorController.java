package com.altf4.AltF4Backend.controller;

import com.altf4.AltF4Backend.dto.LoanCalculationResponseDTO;
import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;
import com.altf4.AltF4Backend.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loan-calculator")
@RequiredArgsConstructor
public class LoanCalculatorController {

    private LoanCalculatorService loanCalculatorService;

    @Autowired
    public LoanCalculatorController (LoanCalculatorService loanCalculatorService) {
        this.loanCalculatorService = loanCalculatorService;
    }


    @PostMapping("/annuity")
    @ResponseBody
    public LoanCalculationResponseDTO returnAnnuityCalculations(@RequestBody EstimatedMonthlyPaymentRequest request){
        return loanCalculatorService.calculateMonthlyAnnuityPayments(request);
    }
}
