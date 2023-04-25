package com.altf4.app.validator;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

import static com.altf4.app.validator.ValidationsConstants.DOWN_PAYMENT_TO_LOAN_RATIO;
import static com.altf4.app.validator.ValidationsConstants.MINIMUM_LOAN_AMOUNT;

public class DownPaymentAndLoanValidator implements ConstraintValidator<DownPaymentAndLoanConstraint, Object> {


    @SneakyThrows
    @Override
    public boolean isValid(Object loan, ConstraintValidatorContext context) {

        int downPayment = getDownPaymentField(loan);
        int totalAmount = getTotalAmountField(loan);

        if (totalAmount < MINIMUM_LOAN_AMOUNT) {
            addConstraintViolation(context, "Loan has to be at least 10000");
            return false;
        }

        if (downPayment >= totalAmount) {
            addConstraintViolation(context, "Down payment cannot be equal or greater than total amount");
            return false;
        }

        if ((double) downPayment / totalAmount < DOWN_PAYMENT_TO_LOAN_RATIO) {
            addConstraintViolation(context, "Down payment must be at least 15% of the total amount");
            return false;
        }

        return true;
    }

    @SneakyThrows
    private int getDownPaymentField(Object loan) {
        Field downPaymentField = loan.getClass().getDeclaredField("downPayment");
        downPaymentField.setAccessible(true);
        return (int) downPaymentField.get(loan);
    }

    @SneakyThrows
    private int getTotalAmountField(Object loan) {
        Field totalAmountField = loan.getClass().getDeclaredField("totalAmount");
        totalAmountField.setAccessible(true);
        return (int) totalAmountField.get(loan);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("downPayment")
                .addConstraintViolation();
    }

}