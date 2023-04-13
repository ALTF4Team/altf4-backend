package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;
import com.altf4.app.model.MonthlyPaymentRequest;

import static com.altf4.app.model.LoanTerms.LOAN_TO_DOWN_PAYMENT_RATIO;

public class LoanToDownPaymentRatioException extends AbstractValidator<MonthlyPaymentRequest> {

    @Override
    public void validate(MonthlyPaymentRequest request) throws ValidationException {

        if ((double)(request.getLoanSize() - request.getDownPayment()) / request.getLoanSize() > LOAN_TO_DOWN_PAYMENT_RATIO) {
            throw new ValidationException("Requested loan's amount it too high for provided down payment." +
                    " Down payment must form at least 85% of requested loan.");
        }
    }
}
