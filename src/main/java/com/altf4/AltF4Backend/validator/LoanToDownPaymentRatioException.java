package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.exception.ValidationException;
import com.altf4.AltF4Backend.model.EstimatedMonthlyPaymentRequest;

public class LoanToDownPaymentRatioException extends AbstractValidator<EstimatedMonthlyPaymentRequest> {
    @Override
    public void validate(EstimatedMonthlyPaymentRequest request) throws ValidationException {

        if ((double)(request.getLoanSize() - request.getDownPayment()) / request.getLoanSize() > 0.85 ) {
            throw new ValidationException("Requested loan's amount it too high for provided down payment." +
                    " Down payment must form at least 85% of requested loan.");
        }
    }
}
