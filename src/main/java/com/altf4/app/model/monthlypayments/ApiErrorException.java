package com.altf4.app.model.monthlypayments;

public class ApiErrorException extends Exception{
    ApiErrorException(){
        super("Api error");
    }
}