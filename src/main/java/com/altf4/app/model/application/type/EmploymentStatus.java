package com.altf4.app.model.application.type;

import com.altf4.app.util.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmploymentStatus {
    CONTRACT_EMPLOYMENT, SELF_EMPLOYED, UNEMPLOYED;

    @JsonCreator
    public static EmploymentStatus capitalValue(String text) {
        return EmploymentStatus.valueOf(StringToEnumConverter.formatEnumValue(text));
    }
}