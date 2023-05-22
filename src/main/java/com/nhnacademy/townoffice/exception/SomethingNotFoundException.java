package com.nhnacademy.townoffice.exception;

public class SomethingNotFoundException extends RuntimeException{
    public SomethingNotFoundException(String s) {
        super(s);
    }
}
