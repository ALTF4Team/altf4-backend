package com.altf4.app.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MonthlyPaymentResponse {

    private int monthlyPaymentAmount;

}
