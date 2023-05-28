package com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username){
        super("User " + username + " already exists!");
    }
}
