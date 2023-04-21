package com.altf4.app.service;

import com.altf4.app.corrector.LoanApplicationDataCorrector;
import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;
    private final LoanApplicationDataCorrector corrector;

    @Autowired
    LoanApplicationService(LoanApplicationRepository repository, LoanApplicationDataCorrector corrector) {
        this.repository = repository;
        this.corrector = corrector;
    }

    public void saveLoanApplication(LoanApplication form) {
        corrector.correctDataInput(form);
        repository.save(form);
    }
}