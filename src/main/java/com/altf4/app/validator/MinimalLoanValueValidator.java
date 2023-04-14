package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;

public class MinimalLoanValueValidator extends AbstractValidator <Integer> {

    @Override
    public void validate(Integer totalAmount) throws ValidationException {

        if (totalAmount < 10000) {
            throw new ValidationException("Requested loan must be at least 10.000 euros.");
        }

    }
}
