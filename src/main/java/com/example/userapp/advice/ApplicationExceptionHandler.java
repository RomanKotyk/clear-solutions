package com.example.userapp.advice;

import com.example.userapp.exception.AgeValidationException;
import com.example.userapp.exception.InvalidDateRangeException;
import com.example.userapp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, ErrorDetails> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), new ErrorDetails(
                    LocalDate.now(),
                    error.getDefaultMessage(),
                    HttpStatus.BAD_REQUEST.value()
            ));
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={InvalidDateRangeException.class, AgeValidationException.class, UserNotFoundException.class})
    public ResponseEntity<?> handleDateException(Exception ex) {
        ErrorDetails details = new ErrorDetails(
          LocalDate.now(),
          ex.getMessage(),
          HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
