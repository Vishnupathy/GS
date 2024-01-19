package com.GreenStitch.CampusPlacements.exceptions;

import jakarta.persistence.RollbackException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* --------------------------------------   Validation Exception    ----------------------------------------------*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> validationExceptionHandler(MethodArgumentNotValidException validationException, WebRequest request) {

        ErrorDetails err = new ErrorDetails();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        err.setTimestamp(LocalDateTime.now().format(formatter));
        err.setDetails("Validation Error...");
        err.setMessage(validationException.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    /*----------------------------------------  NoHandlerFoundException  --------------------------------------*/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException noHandlerFoundException, WebRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ErrorDetails err = new ErrorDetails(LocalDateTime.now().format(formatter), noHandlerFoundException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /*----------------------------------------  RollbackException  --------------------------------------*/
    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<ErrorDetails> rollbackException(RollbackException rollbackException, WebRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ErrorDetails err=new ErrorDetails(LocalDateTime.now().format(formatter), rollbackException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    /*--------------------------------------------  Null Pointer Exception  --------------------------------------------------*/
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> nullPointerExceptionHandler(NullPointerException exception, WebRequest webRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().format(formatter), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /*--------------------------------------------  Exception  --------------------------------------------------*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception exception, WebRequest webRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().format(formatter), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /* --------------------------------------   User Exception    ----------------------------------------------*/
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException userException, WebRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ErrorDetails err = new ErrorDetails(LocalDateTime.now().format(formatter), userException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
