package com.altf4.app.model.calculation;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanTerms {

    public static final double EURIBOR_RATE = 0.03335;
    public static final double MARGIN = 0.025;
    public static final double LOAN_SERVICE_RATIO = 0.4;
    public static final double LOAN_TO_DOWN_PAYMENT_RATIO = 0.85;
}

