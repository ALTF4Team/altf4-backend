package com.altf4.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.altf4.app.validator.ValidationsConstants.MAXIMUM_LOAN_TERM;
import static com.altf4.app.validator.ValidationsConstants.MINIMUM_LOAN_TERM;

public class LoanTermValidator implements ConstraintValidator<LoanTermConstraint, Integer> {

    @Override
    public boolean isValid(Integer termYears, ConstraintValidatorContext context) {
        return termYears <= MAXIMUM_LOAN_TERM && termYears >= MINIMUM_LOAN_TERM;
    }
}