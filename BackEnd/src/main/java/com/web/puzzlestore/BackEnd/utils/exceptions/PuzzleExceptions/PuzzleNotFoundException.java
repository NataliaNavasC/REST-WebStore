package com.web.puzzlestore.BackEnd.utils.exceptions.PuzzleExceptions;

public class PuzzleNotFoundException extends RuntimeException {
    public PuzzleNotFoundException(Long id){
        super("Could not find puzzle by id: " + id);
    }
}
