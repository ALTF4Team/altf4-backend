package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;
import com.altf4.app.model.calculation.LoanCalculationRequest;
import com.altf4.app.model.maxLoan.MaxLoanRequest;

public class NegativeValueValidator extends AbstractValidator<LoanCalculationRequest> {

    @Override
    public void validate(LoanCalculationRequest request) throws ValidationException {
        if (request.getTotalAmount() <= request.getDownPayment()) {
            throw new ValidationException("Loan size must be bigger than down payment");
        }
    }

}
