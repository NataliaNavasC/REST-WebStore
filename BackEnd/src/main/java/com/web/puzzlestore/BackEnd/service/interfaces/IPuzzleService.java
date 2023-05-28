package com.web.puzzlestore.BackEnd.service.interfaces;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPuzzleService {

    Page<Puzzle> getPuzzles(Pageable pageable);

    Puzzle getPuzzleById(Long id);

    Puzzle createPuzzle(Puzzle newPuzzle);

    Puzzle updatePuzzle(Long id, Puzzle puzzleToUpdate);

    void deletePuzzle(Long id);

    Page<Puzzle> getPuzzlesByName(Pageable pageable, String searchCriteria);
    
}
