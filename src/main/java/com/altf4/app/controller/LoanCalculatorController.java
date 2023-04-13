package com.altf4.app.controller;

import com.altf4.app.model.MonthlyPaymentResponse;
import com.altf4.app.model.MonthlyPaymentRequest;
import com.altf4.app.service.LoanCalculatorService;
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
    public MonthlyPaymentResponse returnAnnuityCalculations(@RequestBody MonthlyPaymentRequest request){
        return loanCalculatorService.calculateMonthlyAnnuityPayments(request);
    }
}
