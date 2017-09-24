package com.sampathsl.sms.exception;


public class CustomErrorTypeException extends Exception {

    private String errorMessage;

    public CustomErrorTypeException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
