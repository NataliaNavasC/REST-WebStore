package com.web.puzzlestore.BackEnd.utils.exceptions.PuzzleOrderExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PuzzleOrderNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PuzzleOrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String puzzleNotFoundHandler(PuzzleOrderNotFoundException ex){
        return ex.getMessage();
    }
}
