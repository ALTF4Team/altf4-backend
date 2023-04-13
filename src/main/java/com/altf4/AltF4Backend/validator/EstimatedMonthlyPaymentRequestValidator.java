package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class EstimatedMonthlyPaymentRequestValidator {

    private final MinimalLoanValueValidator minimalLoanValueValidator = new MinimalLoanValueValidator();
    private final LoanTermValidator loanTermValidator = new LoanTermValidator();
    private final NegativeValueValidator negativeValueValidator = new NegativeValueValidator();
    private final LoanToDownPaymentRatioException loanToDownPaymentRatioException = new LoanToDownPaymentRatioException();

    public void validate(EstimatedMonthlyPaymentRequest estimatedMonthlyPaymentRequest) {
        minimalLoanValueValidator.validate(estimatedMonthlyPaymentRequest.getLoanSize());
        loanTermValidator.validate(estimatedMonthlyPaymentRequest.getLoanTermYears());
        negativeValueValidator.validate(estimatedMonthlyPaymentRequest);
        loanToDownPaymentRatioException.validate(estimatedMonthlyPaymentRequest);
    }
}
