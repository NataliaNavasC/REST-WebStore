package com.web.puzzlestore.BackEnd.repository;

import com.web.puzzlestore.BackEnd.model.entities.PuzzleOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPuzzleOrderRepository extends JpaRepository<PuzzleOrder, Long>{
    
}
