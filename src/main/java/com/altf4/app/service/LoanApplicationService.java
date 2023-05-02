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
    private final EmailSenderService emailSenderService;

    @Autowired
    LoanApplicationService(LoanApplicationRepository repository, LoanApplicationDataCorrector corrector, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.corrector = corrector;
        this.emailSenderService = emailSenderService;
    }

    public void saveLoanApplication(LoanApplication form) {
        corrector.correctDataInput(form);
        repository.save(form);
        emailSenderService.sendConfirmationEmail(form);
    }
}