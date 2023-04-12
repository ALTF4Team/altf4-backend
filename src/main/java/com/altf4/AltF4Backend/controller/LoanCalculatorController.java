package com.altf4.AltF4Backend.controller;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;
import com.altf4.AltF4Backend.model.LoanRequest;
import com.altf4.AltF4Backend.model.LoanTerms;
import com.altf4.AltF4Backend.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class LoanCalculatorController {

    private final LoanCalculatorService loanCalculatorService;
    private final LoanTerms loanTerms;

    @PostMapping
    @ResponseBody
    public LoanCalculationResponse postLoanCalculations(@RequestBody LoanRequest loanRequest){
        return loanCalculatorService.calculateAvailableLoanDetails(loanRequest, loanTerms);
    }
}
