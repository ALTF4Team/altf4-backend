package com.altf4.app.model.application;

import lombok.Data;

@Data
public class LoanApplicationForm {

    private Customer customer;

    private Loan loan;

    private FinancialInformation financialInformation;

}