package com.web.puzzlestore.BackEnd.service.implementations;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.repository.IPuzzleRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IPuzzleService;
import com.web.puzzlestore.BackEnd.utils.exceptions.PuzzleExceptions.PuzzleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PuzzleService implements IPuzzleService {

    @Autowired
    private IPuzzleRepository puzzleRepository;

    @Override
    public Page<Puzzle> getPuzzles(Pageable pageable) {
        return this.puzzleRepository.findAll(pageable);
    }

    @Override
    public Puzzle getPuzzleById(Long id) {
        return this.puzzleRepository.findById(id).orElseThrow(()-> new PuzzleNotFoundException(id));
    }

    @Override
    public Puzzle createPuzzle(Puzzle newPuzzle) {
        return this.puzzleRepository.save(newPuzzle);
    }

    @Override
    public Puzzle updatePuzzle(Long id, Puzzle puzzleToUpdate) {
        return this.puzzleRepository.findById(id).map(puzzle -> {
            puzzle.setName(puzzleToUpdate.getName());
            puzzle.setDescription(puzzleToUpdate.getDescription());
            puzzle.setPrice(puzzleToUpdate.getPrice());
            puzzle.setImageSource(puzzleToUpdate.getImageSource());
            return this.puzzleRepository.save(puzzle);
        }).orElseGet(() ->{
            throw new PuzzleNotFoundException(id);
        });
    }

    @Override
    public void deletePuzzle(Long id) {
        try {
            Puzzle puzzleFound = this.getPuzzleById(id);
            this.puzzleRepository.delete(puzzleFound);
        } catch (Exception ex) {
            throw new PuzzleNotFoundException(id);
        }    
    }

    @Override
    public Page<Puzzle> getPuzzlesByName(Pageable pageable, String searchCriteria) {
        return this.puzzleRepository.findByNameContainingIgnoreCase(searchCriteria,pageable);
    }
    
}
