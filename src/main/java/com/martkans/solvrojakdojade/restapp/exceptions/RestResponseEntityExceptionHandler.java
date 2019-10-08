package com.martkans.solvrojakdojade.restapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return new ResponseEntity<Object>("Resource Not Found.\n" + exception.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception exception) {
        return new ResponseEntity<Object>("Bad Request.\n" + exception.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
