package com.altf4.app.service;

import com.altf4.app.model.maxLoan.MaxLoanRequest;
import com.altf4.app.model.maxLoan.MaxLoanResponse;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.maxLoan.MaxLoanTerms.*;

@Service
public class MaxLoanCalculatorService {

    private static MaxLoanResponse buildResponse(int maxLoanAmount, int maxMonthlyInterest) {
        return new MaxLoanResponse().builder().maxLoanAmount(maxLoanAmount).maxMonthlyPayment(maxMonthlyInterest).build();
    }

    public MaxLoanResponse calculateMaxLoanAmount(MaxLoanRequest request) {
        int totalMonthlyIncome = request.getMonthlyIncomeAfterTaxes() - request.getExistingLiabilities();
        double maxLoanAmount = totalMonthlyIncome * loanToServiceRatio * 0.5 * maxTermYears * 12;
        int maxLoanAdjusted = (int) dependentAdjuster(totalMonthlyIncome, request.getNoOfDependents(), maxLoanAmount);
        int maxMonthlyPayment = calculateMaxMonthlyAmount(request);
        if (maxLoanAdjusted <= 0) {
            maxMonthlyPayment = 0;
            maxLoanAdjusted = 0;
        }
        return buildResponse(maxLoanAdjusted, maxMonthlyPayment);
    }

    public double dependentAdjuster(int totalMonthlyAmount, int noOfDependents, double maxLoanAmount) {
        if (totalMonthlyAmount < 500) {
            return 0.0;
        } else if (totalMonthlyAmount < 1100) {
            return maxLoanAmount - (noOfDependents + 1) * dependentMargin;
        } else if (totalMonthlyAmount < 2000) {
            if (noOfDependents == 0) {
                return maxLoanAmount;
            } else if (noOfDependents == 1) {
                return maxLoanAmount - oneDependentAdjusted;
            } else {
                return maxLoanAmount - (noOfDependents - 1) * dependentMargin;
            }
        } else if (totalMonthlyAmount < 2700) {
            if (noOfDependents <= 2) {
                return maxLoanAmount;
            } else {
                return maxLoanAmount - (noOfDependents - 2) * dependentMargin;
            }
        } else if (totalMonthlyAmount < 3000) {
            if (noOfDependents < 6) {
                return maxLoanAmount;
            } else {
                return maxLoanAmount - (noOfDependents - 5) * dependentMargin;
            }
        } else {
            return maxLoanAmount;
        }
    }

    public int calculateMaxMonthlyAmount(MaxLoanRequest request) {
        int totalMonthlyIncome = request.getMonthlyIncomeAfterTaxes() - request.getExistingLiabilities();
        return (int) Math.round(loanToServiceRatio * totalMonthlyIncome);
    }
}
