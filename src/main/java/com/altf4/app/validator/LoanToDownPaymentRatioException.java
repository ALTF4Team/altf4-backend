package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;
import com.altf4.app.model.MonthlyPaymentRequest;

public class LoanToDownPaymentRatioException extends AbstractValidator<MonthlyPaymentRequest> {

    public static final double RATIO = 0.85;

    @Override
    public void validate(MonthlyPaymentRequest request) throws ValidationException {

        if ((double)(request.getLoanSize() - request.getDownPayment()) / request.getLoanSize() > RATIO) {
            throw new ValidationException("Requested loan's amount it too high for provided down payment." +
                    " Down payment must form at least 85% of requested loan.");
        }
    }
}
