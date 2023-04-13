package com.altf4.app.controller;

import com.altf4.app.model.MonthlyPaymentFormResponse;
import com.altf4.app.model.MonthlyPaymentFormRequest;
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


    @PostMapping
    @ResponseBody
    public MonthlyPaymentFormResponse processMonthlyPaymentForm(@RequestBody MonthlyPaymentFormRequest request){
        return loanCalculatorService.generateMonthlyPaymentFormResponse(request);
    }
}
