package com.altf4.app.model.monthlypayments;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanTerms {

    public static final double MARGIN = 0.025;

    public static final double DEFAULT_EURIBOR = 0.03335;

}