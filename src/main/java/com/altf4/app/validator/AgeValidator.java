package com.altf4.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

import static com.altf4.app.validator.ValidationsConstants.MINIMAL_AGE;

public class AgeValidator implements ConstraintValidator<AgeConstraint, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {

        Period period = Period.between(dateOfBirth, LocalDate.now());

        int age = period.getYears();

        return age >= MINIMAL_AGE;
    }
}