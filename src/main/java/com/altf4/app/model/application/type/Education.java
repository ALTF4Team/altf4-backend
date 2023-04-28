package com.altf4.app.model.application.type;

import com.altf4.app.util.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Education {
    PRIMARY, SECONDARY, VOCATIONAL, BACHELOR, MASTER, DOCTORAL;

    @JsonCreator
    public static Education capitalValue(String text) {
        return Education.valueOf(StringToEnumConverter.formatEnumValue(text));
    }
}