package com.altf4.app.model.calculation;

public class ApiErrorException extends Exception{
    public ApiErrorException() {
       super("Error from API");
    }
}
