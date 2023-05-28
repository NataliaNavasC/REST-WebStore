package com.web.puzzlestore.BackEnd.utils.exceptions.PurchaseExceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(long id){
        super("Could not find puchase by id: " + id);
    }
}
