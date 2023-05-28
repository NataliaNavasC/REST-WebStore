package com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username){
        super("Could not find user by username: " + username);
    }
}
