package com.altf4.app.service;

import com.altf4.app.model.maxLoan.maxLoanRequest;
import com.altf4.app.model.maxLoan.maxLoanResponse;
import com.altf4.app.model.maxLoan.maxLoanTerms;

import static com.altf4.app.model.maxLoan.maxLoanTerms.*;

public class MaxLoanCalculatorService {



    public int calculateMaxLoanAmount(maxLoanRequest request){
        int totalMonthlyIncome = request.getMonthlyIncomeAfterTaxes() - request.getExistingLiabilities();
        if(request.getNoOfDependents() ==0 ){
            return (int) Math.round(loanToServiceRatio*totalMonthlyIncome);
        }
        else if(request.getNoOfDependents() == 1){
            return (int) Math.round(loanToServiceRatio*totalMonthlyIncome*oneDependentMultiplier*maxTermYears);
        }
        else if(request.getNoOfDependents() ==2){
            return (int) Math.round(loanToServiceRatio*totalMonthlyIncome*twoDependentMultiplier*maxTermYears);
        }
        else{
            return (int) Math.round(loanToServiceRatio * totalMonthlyIncome * moreThanTwoDependentMultiplier*maxTermYears);
        }
    }

    public int calculateMaxMonthlyAmount(maxLoanRequest request){
        int totalMonthlyIncome = request.getMonthlyIncomeAfterTaxes() - request.getExistingLiabilities();
        return (int) Math.round(loanToServiceRatio*totalMonthlyIncome);
    }
}
