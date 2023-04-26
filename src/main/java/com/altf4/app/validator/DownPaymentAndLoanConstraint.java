package com.altf4.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DownPaymentAndLoanValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DownPaymentAndLoanConstraint {

    String message() default "Requested loan's amount it too high for provided down payment. " +
            "Down payment must form at least 15% of requested loan.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
