package com.altf4.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FinancialInformationValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FinancialInformationConstraint {

    String message() default "Please correctly fill information according to employment status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
