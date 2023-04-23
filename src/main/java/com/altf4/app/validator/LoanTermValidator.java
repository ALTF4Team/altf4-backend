package com.altf4.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoanTermValidator implements ConstraintValidator<LoanTermConstraint, Integer> {

    @Override
    public boolean isValid(Integer termYears, ConstraintValidatorContext context) {
        return termYears <= 30 && termYears >= 1;
    }
}