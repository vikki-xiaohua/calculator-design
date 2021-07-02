package com.sezzle.calculator.exception;

public class UnsupportedOperatorInputException
        extends RuntimeException {
    public UnsupportedOperatorInputException(String errorMessage) {
        super(errorMessage);
    }
}