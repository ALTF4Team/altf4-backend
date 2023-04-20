package com.altf4.app.model.application.type;

import com.altf4.app.util.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum MaritalStatus {
    SINGLE, MARRIED, DIVORCED, WIDOW;

    @JsonCreator
    public static MaritalStatus capitalValue(String text) {
        return MaritalStatus.valueOf(StringToEnumConverter.formatEnumValue(text));
    }
}