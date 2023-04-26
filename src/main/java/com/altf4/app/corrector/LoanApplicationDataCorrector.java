package com.altf4.app.corrector;

import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.model.application.type.EmploymentStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static com.altf4.app.model.application.type.ApplicationStatus.PENDING;

@Component
public class LoanApplicationDataCorrector {

    LoanApplication loanApplication;

    @Autowired
    LoanApplicationDataCorrector(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public void correctDataInput(LoanApplication loanApplication) {

        loanApplication.setApplicationStatus(PENDING);

        correctUserInput(loanApplication);
    }

    private void correctUserInput(LoanApplication loanApplication) {

        capitalizeData(loanApplication);

        clearIrrelevantFields(loanApplication);
    }


    private void capitalizeData(LoanApplication loanApplication) {
        capitalizeDataField(loanApplication.getCustomer(), "name");
        capitalizeDataField(loanApplication.getCustomer(), "surname");
    }

    private void clearIrrelevantFields(LoanApplication loanApplication) {
        EmploymentStatus employmentStatus = loanApplication.getFinancialInformation().getEmploymentStatus();
        switch (employmentStatus) {
            case CONTRACT_EMPLOYMENT -> clearNonContractEmploymentFields(loanApplication);
            case SELF_EMPLOYED -> clearNonSelfEmploymentFields(loanApplication);
            case UNEMPLOYED -> clearNonUnemployedFields(loanApplication);
        }
    }

    private void clearNonContractEmploymentFields(LoanApplication loanApplication) {

        clearSelfEmploymentFields(loanApplication);

        clearUnemployedFields(loanApplication);
    }

    private void clearNonSelfEmploymentFields(LoanApplication loanApplication) {

        clearContractEmploymentFields(loanApplication);

        clearUnemployedFields(loanApplication);
    }

    private void clearNonUnemployedFields(LoanApplication loanApplication) {

        clearContractEmploymentFields(loanApplication);

        clearSelfEmploymentFields(loanApplication);
    }

    @SneakyThrows
    public void capitalizeDataField(Object obj, String fieldName) {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        String name = (String) field.get(obj);
        String capitalized = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
        field.set(obj, capitalized);
    }

    private static void clearContractEmploymentFields(LoanApplication loanApplication) {
        loanApplication.getFinancialInformation().setYearsCurrentEmployer(null);
        loanApplication.getFinancialInformation().setCurrentEmployer(null);
        loanApplication.getFinancialInformation().setEmploymentContractType(null);
        loanApplication.getFinancialInformation().setIndustry(null);
        loanApplication.getFinancialInformation().setPosition(null);
    }

    private static void clearSelfEmploymentFields(LoanApplication loanApplication) {
        loanApplication.getFinancialInformation().setYearsSelfEmployment(null);
    }

    private static void clearUnemployedFields(LoanApplication loanApplication) {
        loanApplication.getFinancialInformation().setSourceOfIncome(null);
    }
}