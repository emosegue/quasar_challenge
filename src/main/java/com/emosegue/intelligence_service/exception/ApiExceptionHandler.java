package com.emosegue.intelligence_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotInformationException.class})
    public ResponseEntity<Object> handleNotInformationException(NotInformationException e){
        ApiException apiException = new ApiException(LocalDateTime.now(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(value = {MessageException.class})
    public ResponseEntity<Object> handleMessageException(MessageException e){
        ApiException apiException = new ApiException(LocalDateTime.now(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(value = {LocationException.class})
    public ResponseEntity<Object> handleLocationException(MessageException e){
        ApiException apiException = new ApiException(LocalDateTime.now(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    };

}
