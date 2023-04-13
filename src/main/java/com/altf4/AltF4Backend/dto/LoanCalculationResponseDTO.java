package com.altf4.AltF4Backend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanCalculationResponseDTO {

    private int monthlyPaymentAmount;

}
