package com.altf4.app.model.calculation;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanTerms {

    public static final double EURIBOR_RATE = 0.03335;
    public static final double MARGIN = 0.025;

}