package com.altf4.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeConstraint {

    String message() default "Please input valid date of birth";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}