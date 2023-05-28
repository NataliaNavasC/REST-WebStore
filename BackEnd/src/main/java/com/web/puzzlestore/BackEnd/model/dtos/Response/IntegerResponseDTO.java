package com.web.puzzlestore.BackEnd.model.dtos.Response;

public class IntegerResponseDTO {
    private int value;

    public IntegerResponseDTO() {
    }

    public IntegerResponseDTO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
}
