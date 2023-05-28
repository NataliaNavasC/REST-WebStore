package com.web.puzzlestore.BackEnd.service.interfaces;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.model.entities.PuzzleOrder;

public interface IPuzzleOrderService {
    public PuzzleOrder getById(long id);
    public PuzzleOrder creatPuzzleOrder(PuzzleOrder newPuzzleOrder);
    public void deletePuzzleOrder(long puzzleOrderId);
    public PuzzleOrder updatePuzzleOrder(PuzzleOrder puzzleOrderUpdated);
}
