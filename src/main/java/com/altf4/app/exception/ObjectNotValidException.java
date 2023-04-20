package com.altf4.app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@Data
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ObjectNotValidException extends RuntimeException {

    private final Set<String> errorMessages;
}
