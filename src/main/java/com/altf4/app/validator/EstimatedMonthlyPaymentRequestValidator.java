package com.altf4.app.validator;

import com.altf4.app.model.MonthlyPaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class EstimatedMonthlyPaymentRequestValidator {

    private final MinimalLoanValueValidator minimalLoanValueValidator = new MinimalLoanValueValidator();
    private final LoanTermValidator loanTermValidator = new LoanTermValidator();
    private final NegativeValueValidator negativeValueValidator = new NegativeValueValidator();
    private final LoanToDownPaymentRatioException loanToDownPaymentRatioException = new LoanToDownPaymentRatioException();

    public void validate(MonthlyPaymentRequest monthlyPaymentRequest) {
        minimalLoanValueValidator.validate(monthlyPaymentRequest.getLoanSize());
        loanTermValidator.validate(monthlyPaymentRequest.getLoanTermYears());
        negativeValueValidator.validate(monthlyPaymentRequest);
        loanToDownPaymentRatioException.validate(monthlyPaymentRequest);
    }
}
