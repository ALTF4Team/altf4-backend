package com.altf4.app.util;

import com.altf4.app.model.application.type.ApplicationStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestParamEnumConverter implements Converter<String, ApplicationStatus> {

    @Override
    public ApplicationStatus convert(String value) {
        return ApplicationStatus.valueOf(value.toUpperCase());
    }
}
