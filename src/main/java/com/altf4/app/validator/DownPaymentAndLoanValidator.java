package com.altf4.app.validator;

import com.altf4.app.model.application.Loan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.altf4.app.model.calculation.LoanTerms.DOWN_PAYMENT_TO_LOAN_RATIO;

public class DownPaymentAndLoanValidator implements ConstraintValidator<DownPaymentAndLoanConstraint, Loan> {


    @Override
    public boolean isValid(Loan loan, ConstraintValidatorContext context) {

        int downPayment = loan.getDownPayment();
        int totalAmount = loan.getTotalAmount();

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