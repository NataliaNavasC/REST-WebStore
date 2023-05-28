package com.web.puzzlestore.BackEnd.utils.exceptions.PuzzleOrderExceptions;

public class PuzzleOrderNotFoundException extends RuntimeException {
    public PuzzleOrderNotFoundException(long id){
        super("Puzzle order with id " + id + " does not exists");
    }
}
