package com.altf4.app.model.monthlypayments;

import com.altf4.app.validator.DownPaymentAndLoanConstraint;
import com.altf4.app.validator.LoanTermConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DownPaymentAndLoanConstraint
public class MonthlyPaymentsRequest {

    private int totalAmount;

    private int downPayment;

    @LoanTermConstraint
    private int termYears;

}