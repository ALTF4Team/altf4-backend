package com.altf4.app.service;

import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.model.application.type.ApplicationStatus;
import com.altf4.app.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.altf4.app.model.application.type.ApplicationStatus.APPROVED;
import static com.altf4.app.model.application.type.ApplicationStatus.DENIED;

@Service
public class AdminService {

    private final LoanApplicationRepository repository;
    private final EmailSenderService emailSenderService;

    @Autowired
    public AdminService(LoanApplicationRepository repository, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.emailSenderService = emailSenderService;
    }


    public List<LoanApplication> getLoanApplications() {
        return repository.findAllByOrderByApplicationStatusDesc();
    }

    public LoanApplication getLoanApplicationById(int id) {
        return repository.findById(id);
    }

    public void updateApplicationStatus(int id, ApplicationStatus status) {
        LoanApplication application = repository.findById(id);
        application.setApplicationStatus(status);

        if (!application.isResponseEmailSent() && (status == APPROVED || status == DENIED)) {
            emailSenderService.sendDecisionEmail(application);
        }

        repository.save(application);


    }

    public void deleteAllApplications() {
        repository.deleteAll();
    }

    public List<LoanApplication> searchLoanApplicationsByCustomer(String name) {
        return repository.searchLoanApplicationsByCustomer(name);
    }
}