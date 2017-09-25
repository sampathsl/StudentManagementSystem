package com.sampathsl.sms.exception;

public class DateFormatConversionException extends Exception {

    private String errorMessage;

    public DateFormatConversionException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
