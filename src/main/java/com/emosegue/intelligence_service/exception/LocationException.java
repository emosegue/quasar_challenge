package com.emosegue.intelligence_service.exception;

/**
 * @author emosegue
 */
public class LocationException extends RuntimeException {

    public LocationException(String message) {
        super(message);
    }

    public LocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
