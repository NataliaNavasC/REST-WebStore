package com.web.puzzlestore.BackEnd.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PuzzleOrder {
    @Id
    @GeneratedValue
    private Long id;
    @Basic
    @Column(nullable = false)
    private int count;
    @OneToOne(fetch = FetchType.EAGER)
    private Puzzle puzzle;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Puzzle getPuzzle() {
        return puzzle;
    }
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
}
