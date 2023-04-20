package com.altf4.app.model.application.type;

import com.altf4.app.util.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmploymentContractType {
    OPEN_ENDED, FIXED_TERM;

    @JsonCreator
    public static EmploymentContractType capitalValue(String text) {
        return EmploymentContractType.valueOf(StringToEnumConverter.formatEnumValue(text));
    }
}