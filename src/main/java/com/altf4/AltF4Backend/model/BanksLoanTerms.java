package com.altf4.AltF4Backend.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BanksLoanTerms {

    private double euriborRate = 0.03335;

    private double margin = 0.025;

    private double loanServiceRatio;

    private double loanToValueRatio;

}
