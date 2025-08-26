package com.example.demo.exceptions;


import com.example.demo.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> ResourceNotFoundHandler(
            ResourceNotFound ex,
            WebRequest req
    ) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> AllExceptionsHandler(
            Exception ex,
            WebRequest req
    ) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
