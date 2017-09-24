package com.sampathsl.sms.exception;

public class StudentNotFountException extends Exception {
	
	private static final long serialVersionUID = 15345346356542L;

	// Parameter less Constructor
    public StudentNotFountException() {}

    // Constructor that accepts a message
    public StudentNotFountException(String message) {
       super(message);
    }
	
}
