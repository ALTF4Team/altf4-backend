package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.exception.ValidationException;

public class MinimalLoanValueValidator extends AbstractValidator <Integer> {

    @Override
    public void validate(Integer loanSize) throws ValidationException {

        if (loanSize < 10000) {
            throw new ValidationException("Requested loan must be at least 10.000 euros.");
        }

    }
}
