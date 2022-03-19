package com.emosegue.intelligence_service.exception;

/**
 * @author emosegue
 */
public class MessageException extends RuntimeException {

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
