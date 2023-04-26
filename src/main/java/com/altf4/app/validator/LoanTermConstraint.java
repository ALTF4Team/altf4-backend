package com.altf4.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoanTermValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoanTermConstraint {

    String message() default "Requested loan term must be between 1 and 30 years.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
