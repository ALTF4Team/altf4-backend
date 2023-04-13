package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;
import com.altf4.app.model.MonthlyPaymentFormRequest;

public class NegativeValueValidator extends AbstractValidator<MonthlyPaymentFormRequest> {

    @Override
    public void validate(MonthlyPaymentFormRequest request) throws ValidationException {
        if (request.getTotalAmount() <= request.getDownPayment()) {
            throw new ValidationException("Loan size must be bigger than down payment");
        }
    }
}
