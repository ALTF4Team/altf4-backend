package com.altf4.app.repository;

import com.altf4.app.model.application.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {

    LoanApplication findById(int id);

    List<LoanApplication> findAllByOrderByApplicationStatusDesc();

    default List<LoanApplication> searchLoanApplicationsByCustomer(String searchName) {
        return findAllByCustomerNameContainingOrCustomerSurnameContainingOrderByApplicationStatusDesc(searchName, searchName);
    }
    List<LoanApplication> findAllByCustomerNameContainingOrCustomerSurnameContainingOrderByApplicationStatusDesc(String name, String surname);

}