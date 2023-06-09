package com.altf4.app.model.application.type;

import com.altf4.app.util.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoanPurpose {
    MORTGAGE;

    @JsonCreator
    public static LoanPurpose capitalValue(String text) {
        return LoanPurpose.valueOf(StringToEnumConverter.formatEnumValue(text));
    }
}