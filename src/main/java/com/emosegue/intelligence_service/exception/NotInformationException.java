package com.emosegue.intelligence_service.exception;


public class NotInformationException extends RuntimeException{
    public NotInformationException(String message) {
        super(message);
    }

    public NotInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
