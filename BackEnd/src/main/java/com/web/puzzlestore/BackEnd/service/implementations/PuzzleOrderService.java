package com.web.puzzlestore.BackEnd.service.implementations;

import com.web.puzzlestore.BackEnd.model.entities.PuzzleOrder;
import com.web.puzzlestore.BackEnd.repository.IPuzzleOrderRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IPuzzleOrderService;
import com.web.puzzlestore.BackEnd.utils.exceptions.PuzzleOrderExceptions.PuzzleOrderNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuzzleOrderService implements IPuzzleOrderService {

    @Autowired
    private IPuzzleOrderRepository puzzleOrderRepository;

    @Override
    public PuzzleOrder creatPuzzleOrder(PuzzleOrder newPuzzleOrder) {
        return puzzleOrderRepository.save(newPuzzleOrder);
    }

    @Override
    public void deletePuzzleOrder(long puzzleOrderId) {
        PuzzleOrder puzzleOrderFound = this.getById(puzzleOrderId);
        this.puzzleOrderRepository.delete(puzzleOrderFound);
    }

    @Override
    public PuzzleOrder updatePuzzleOrder(PuzzleOrder puzzleOrderUpdated) {
        PuzzleOrder puzzleOrderFound = this.getById(puzzleOrderUpdated.getId());
        puzzleOrderFound.setCount(puzzleOrderFound.getCount());
        return this.puzzleOrderRepository.save(puzzleOrderFound);
    }

    @Override
    public PuzzleOrder getById(long id) {
        return this.puzzleOrderRepository.findById(id).orElseThrow(()-> new PuzzleOrderNotFoundException(id));
    }
}
