package com.altf4.app.model.maxLoan;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaxLoanRequest {
    private int monthlyIncomeAfterTaxes;
    private int existingLiabilities;
    private int noOfDependents;
}
