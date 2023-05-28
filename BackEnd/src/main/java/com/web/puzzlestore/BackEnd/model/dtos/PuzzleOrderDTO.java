package com.web.puzzlestore.BackEnd.model.dtos;

public class PuzzleOrderDTO {
    private Long id;
    private int count;
    private PuzzleDTO puzzle;

    public PuzzleOrderDTO(Long id, int count, PuzzleDTO puzzle) {
        this.id = id;
        this.count = count;
        this.puzzle = puzzle;
    }
    public PuzzleOrderDTO() {
    }
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
    public PuzzleDTO getPuzzle() {
        return puzzle;
    }
    public void setPuzzle(PuzzleDTO puzzle) {
        this.puzzle = puzzle;
    }

    
}
