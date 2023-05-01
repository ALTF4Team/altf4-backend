package com.altf4.app.model.monthlypayments;

public class EuriborWrapper {
    private Euribor[] non_central_bank_rates;

    public Euribor[] getNon_central_bank_rates() {
        return non_central_bank_rates;
    }

    public void setNon_central_bank_rates(Euribor[] non_central_bank_rates) {
        this.non_central_bank_rates = non_central_bank_rates;
    }
}