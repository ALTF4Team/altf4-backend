package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;
import com.altf4.app.model.MonthlyPaymentRequest;

public class NegativeValueValidator extends AbstractValidator<MonthlyPaymentRequest> {

    @Override
    public void validate(MonthlyPaymentRequest request) throws ValidationException {
        if (request.getLoanSize() <= request.getDownPayment()) {
            throw new ValidationException("Loan size must be bigger than down payment");
        }
    }
}
