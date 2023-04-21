package com.altf4.app.corrector;

import com.altf4.app.model.application.LoanApplication;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static com.altf4.app.model.application.type.ApplicationStatus.PENDING;

@Component
public class LoanApplicationDataCorrector {

    public void correctDataInput(LoanApplication form) {
        form.setApplicationStatus(PENDING);
        capitalizeData(form);
    }

    private void capitalizeData(LoanApplication form) {
        capitalizeDataField(form.getCustomer(), "name");
        capitalizeDataField(form.getCustomer(), "surname");
    }

    @SneakyThrows
    public void capitalizeDataField(Object obj, String fieldName) {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        String name = (String) field.get(obj);
        String capitalized = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
        field.set(obj, capitalized);
    }
}