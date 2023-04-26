package com.altf4.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryOfCitizenshipValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryOfCitizenshipConstraint {

    String message() default "Please input valid country";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
