package com.example.seconddemo.exception;

public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException() {
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
