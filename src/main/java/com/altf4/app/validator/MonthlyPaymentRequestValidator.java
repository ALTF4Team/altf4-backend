package com.altf4.app.validator;

import com.altf4.app.model.MonthlyPaymentFormRequest;
import org.springframework.stereotype.Component;

@Component
public class MonthlyPaymentRequestValidator {

    private final MinimalLoanValueValidator minimalLoanValueValidator = new MinimalLoanValueValidator();
    private final LoanTermValidator loanTermValidator = new LoanTermValidator();
    private final NegativeValueValidator negativeValueValidator = new NegativeValueValidator();
    private final LoanToDownPaymentRatioException loanToDownPaymentRatioException = new LoanToDownPaymentRatioException();

    public void validate(MonthlyPaymentFormRequest monthlyPaymentFormRequest) {
        minimalLoanValueValidator.validate(monthlyPaymentFormRequest.getTotalAmount());
        loanTermValidator.validate(monthlyPaymentFormRequest.getTermYears());
        negativeValueValidator.validate(monthlyPaymentFormRequest);
        loanToDownPaymentRatioException.validate(monthlyPaymentFormRequest);
    }
}
