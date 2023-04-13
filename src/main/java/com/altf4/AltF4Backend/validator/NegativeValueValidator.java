package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.exception.ValidationException;
import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;

public class NegativeValueValidator extends AbstractValidator<EstimatedMonthlyPaymentRequest> {

    @Override
    public void validate(EstimatedMonthlyPaymentRequest request) throws ValidationException {
        if (request.getLoanSize() <= request.getDownPayment()) {
            throw new ValidationException("Loan size must be bigger than down payment");
        }
    }
}
