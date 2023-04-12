package com.altf4.AltF4Backend.controller;

import com.altf4.AltF4Backend.dto.LoanCalculationResponse;
import com.altf4.AltF4Backend.model.LoanRequest;
import com.altf4.AltF4Backend.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class LoanCalculatorController {

    private final LoanCalculatorService loanCalculatorService;

    @GetMapping()
    public LoanCalculationResponse getLoanCalculations() {

        //return LoanCalculationResponse;  needs some actual logic
    }

    @PostMapping
    public void save(@RequestBody LoanRequest loanRequest){
        loanCalculatorService.calculateAvailableLoanDetails();
        getLoanCalculations();  //this is GetMapping(). can you do it like this?
    }
}
