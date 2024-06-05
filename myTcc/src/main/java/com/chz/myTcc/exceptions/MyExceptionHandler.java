package com.chz.myTcc.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        log.error("MyExceptionHandler::handleException: " + e.getMessage());
        return e.getClass().getSimpleName() + ": " + e.getMessage();
    }
}
