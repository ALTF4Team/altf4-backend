package com.altf4.app.model.calculation;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanTerms {
    static CallToApi callToApi = new CallToApi();
    public static double EURIBOR_RATE = callToApi.euriborRate();;
    public static final double MARGIN = 0.025;
    public static final double LOAN_SERVICE_RATIO = 0.4;
    public static final double LOAN_TO_DOWN_PAYMENT_RATIO = 0.85;
}

