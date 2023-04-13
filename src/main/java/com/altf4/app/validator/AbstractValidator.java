package com.altf4.app.validator;

import com.altf4.app.exception.ValidationException;

public abstract class AbstractValidator<T> {

    private T t;

    public abstract void validate(T t) throws ValidationException;
}
