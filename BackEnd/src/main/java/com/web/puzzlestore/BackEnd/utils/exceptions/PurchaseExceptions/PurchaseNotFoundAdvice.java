package com.web.puzzlestore.BackEnd.utils.exceptions.PurchaseExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PurchaseNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PurchaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String puchaseNotFoundHandler(PurchaseNotFoundException ex){
        return ex.getMessage();
    }   
}
