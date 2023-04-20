package com.altf4.app.service;

import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.repository.LoanApplicationRepository;

import com.altf4.app.validator.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.altf4.app.model.application.type.ApplicationStatus.PENDING;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;
    private final ApplicationValidator<LoanApplication> validator;

    @Autowired
    LoanApplicationService(LoanApplicationRepository repository, ApplicationValidator<LoanApplication> validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public void saveLoanApplication(LoanApplication form) {
        form.setApplicationStatus(PENDING);

        System.out.println(form.getFinancialInformation().getEmploymentStatus());
        validator.validate(form);
        repository.save(form);
    }
}