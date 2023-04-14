package com.altf4.app.validator;

import com.altf4.app.model.calculation.LoanCalculationRequest;
import org.springframework.stereotype.Component;

@Component
public class MonthlyPaymentRequestValidator {

    private final MinimalLoanValueValidator minimalLoanValueValidator = new MinimalLoanValueValidator();
    private final LoanTermValidator loanTermValidator = new LoanTermValidator();
    private final NegativeValueValidator negativeValueValidator = new NegativeValueValidator();
    private final LoanToDownPaymentRatioException loanToDownPaymentRatioException = new LoanToDownPaymentRatioException();

    public void validate(LoanCalculationRequest loanCalculationRequest) {
        minimalLoanValueValidator.validate(loanCalculationRequest.getTotalAmount());
        loanTermValidator.validate(loanCalculationRequest.getTermYears());
        negativeValueValidator.validate(loanCalculationRequest);
        loanToDownPaymentRatioException.validate(loanCalculationRequest);
    }
}
