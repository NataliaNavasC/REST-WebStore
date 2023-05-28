package com.web.puzzlestore.BackEnd.model.dtos;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {
    private Long id;
    private UserDTO user;
    private List<PuzzleOrderDTO> puzzleOrders;

    public ShoppingCartDTO(Long id, UserDTO user, List<PuzzleOrderDTO> puzzleOrders) {
        this.id = id;
        this.user = user;
        this.puzzleOrders = puzzleOrders;
    }
    public ShoppingCartDTO() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public List<PuzzleOrderDTO> getPuzzleOrders() {
        if (puzzleOrders == null) {
            puzzleOrders = new ArrayList<>();
        }
        return puzzleOrders;
    }
    public void setPuzzleOrders(List<PuzzleOrderDTO> puzzleOrders) {
        this.puzzleOrders = puzzleOrders;
    }

    
}
