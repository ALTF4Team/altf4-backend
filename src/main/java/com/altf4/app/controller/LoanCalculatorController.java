package com.altf4.app.controller;

import com.altf4.app.model.calculation.LoanCalculationRequest;
import com.altf4.app.model.calculation.LoanCalculationResponse;
import com.altf4.app.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan-calculator")
@RequiredArgsConstructor
public class LoanCalculatorController {

    private LoanCalculatorService loanCalculatorService;

    @Autowired
    public LoanCalculatorController(LoanCalculatorService loanCalculatorService) {
        this.loanCalculatorService = loanCalculatorService;
    }

    @PostMapping
    @ResponseBody
    public LoanCalculationResponse getLoanCalculations(@RequestBody @Valid LoanCalculationRequest request) {
        return loanCalculatorService.getLoanCalculations(request);
    }
}