package com.altf4.app.controller;

import com.altf4.app.model.maxLoan.MaxLoanRequest;
import com.altf4.app.model.maxLoan.MaxLoanResponse;
import com.altf4.app.service.MaxLoanCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maxloan")
public class MaxLoanController {
    private MaxLoanCalculatorService maxLoanCalculatorService;
    @Autowired
    public MaxLoanController(MaxLoanCalculatorService maxLoanCalculatorService) {
        this.maxLoanCalculatorService = maxLoanCalculatorService;
    }

    @PostMapping
    @ResponseBody
    public MaxLoanResponse getMaxLoanCalculations(@RequestBody MaxLoanRequest request){
        return maxLoanCalculatorService.calculateMaxLoanAmount(request);
    }
}
