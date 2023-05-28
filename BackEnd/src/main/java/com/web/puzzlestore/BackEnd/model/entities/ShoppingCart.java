package com.web.puzzlestore.BackEnd.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author User
 */
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PuzzleOrder> puzzleOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PuzzleOrder> getPuzzleOrders() {
        return puzzleOrders;
    }

    public void setPuzzleOrders(List<PuzzleOrder> puzzleOrders) {
        this.puzzleOrders = puzzleOrders;
    }

    public void addPuzzleOrder(PuzzleOrder puzzleOrder) {
        getPuzzleOrders().add(puzzleOrder);
    }

    public void removePuzzleOrder(PuzzleOrder puzzleOrder) {
        getPuzzleOrders().remove(puzzleOrder);
    }

}