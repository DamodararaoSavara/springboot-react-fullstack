package com.cricket.cricketers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePlayerNotExist(PlayerNotFoundException playerNotFoundException,
                                                             WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                LocalDateTime.now(),
                playerNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"

        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
