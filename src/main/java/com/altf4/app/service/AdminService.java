package com.altf4.app.service;

import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.model.application.type.ApplicationStatus;
import com.altf4.app.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final LoanApplicationRepository repository;

    @Autowired
    public AdminService(LoanApplicationRepository repository) {
        this.repository = repository;
    }


    public List<LoanApplication> getLoanApplications() {
        return repository.findAll();
    }

    public LoanApplication getLoanApplicationById(int id) {
        return repository.findById(id);
    }

    public void updateApplicationStatus(int id, ApplicationStatus status) {
        LoanApplication application = repository.findById(id);
        application.setApplicationStatus(status);
        repository.save(application);
    }

    public void deleteAllApplications() {
        repository.deleteAll();
    }
}