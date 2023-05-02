package com.altf4.app.controller;

import com.altf4.app.model.application.LoanApplication;
import com.altf4.app.model.application.type.ApplicationStatus;
import com.altf4.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/applications")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public List<LoanApplication> getLoanApplications() {
        return adminService.getLoanApplications();
    }

    @GetMapping("/{id}")
    public LoanApplication getLoanApplicationById(@PathVariable int id) {
        return adminService.getLoanApplicationById(id);
    }

    @PutMapping("/{id}")
    public void updateApplicationStatus(@PathVariable int id, ApplicationStatus status) {
        adminService.updateApplicationStatus(id, status);
    }

    @GetMapping("/search")
    public List<LoanApplication> searchLoanApplicationsByCustomer(@Param("name") String name) {
        return adminService.searchLoanApplicationsByCustomer(name);
    }
}