package com.altf4.app.validator;

import com.altf4.app.model.application.Loan;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

import static com.altf4.app.model.calculation.LoanTerms.DOWN_PAYMENT_TO_LOAN_RATIO;

public class DownPaymentAndLoanValidator implements ConstraintValidator<DownPaymentAndLoanConstraint, Object> {


    @SneakyThrows
    @Override
    public boolean isValid(Object loan, ConstraintValidatorContext context) {

        Field downPaymentField = loan.getClass().getDeclaredField("downPayment");
        Field totalAmountField = loan.getClass().getDeclaredField("totalAmount");

        downPaymentField.setAccessible(true);
        totalAmountField.setAccessible(true);

        int downPayment = (int) downPaymentField.get(loan);
        int totalAmount = (int) totalAmountField.get(loan);

        if (totalAmount < 10000) {
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

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("downPayment")
                .addConstraintViolation();
    }

}