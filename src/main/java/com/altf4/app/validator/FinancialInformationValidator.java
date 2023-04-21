package com.altf4.app.validator;

import com.altf4.app.model.application.FinancialInformation;
import com.altf4.app.model.application.type.EmploymentStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FinancialInformationValidator implements
        ConstraintValidator<FinancialInformationConstraint, FinancialInformation> {

    @Override
    public boolean isValid(FinancialInformation form, ConstraintValidatorContext context) {
        if (form == null) {
            return true;
        }

        EmploymentStatus employmentStatus = form.getEmploymentStatus();

        if (employmentStatus == null) {
            return true;
        }

        return switch (employmentStatus) {
            case CONTRACT_EMPLOYMENT -> validateContractEmployment(form);
            case SELF_EMPLOYED -> validateSelfEmployment(form);
            case UNEMPLOYED -> validateUnemployment(form);
        };
    }


    private static boolean validateContractEmployment(FinancialInformation form) {
        return  form.getCurrentEmployer() != null &&
                !Objects.equals(form.getCurrentEmployer().trim(), "") &&
                form.getYearsCurrentEmployer() != null &&
                form.getYearsCurrentEmployer() > 0 &&
                form.getYearsCurrentEmployer() <= 70 &&
                form.getEmploymentContractType() != null;
    }

    private static boolean validateSelfEmployment(FinancialInformation form) {
        return  form.getYearsSelfEmployment() != null &&
                form.getYearsSelfEmployment() > 0 &&
                form.getYearsSelfEmployment() <= 70;
    }

    private static boolean validateUnemployment(FinancialInformation form) {
        return  form.getSourceOfIncome() != null &&
                !Objects.equals(form.getSourceOfIncome().trim(), "");
    }

}