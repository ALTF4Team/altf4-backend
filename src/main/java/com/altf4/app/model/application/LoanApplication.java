package com.altf4.app.model.application;

import com.altf4.app.model.application.type.ApplicationStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "application")
@Component
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private LocalDateTime timeCreated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_information_id")
    private FinancialInformation financialInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coborrower_id")
    private Customer coBorrower;

}