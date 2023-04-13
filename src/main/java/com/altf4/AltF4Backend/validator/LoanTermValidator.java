package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.exception.ValidationException;

public class LoanTermValidator extends AbstractValidator<Integer> {
    @Override
    public void validate(Integer loanTermYears) throws ValidationException {

        if (loanTermYears > 30 || loanTermYears < 1) {
            throw new ValidationException("Requested loan term must be between 1 and 30 years.");
        }

    }
}
