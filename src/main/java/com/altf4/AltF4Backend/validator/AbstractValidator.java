package com.altf4.AltF4Backend.validator;

import com.altf4.AltF4Backend.exception.ValidationException;

public abstract class AbstractValidator<T> {

    private T t;

    public abstract void validate(T t) throws ValidationException;
}
