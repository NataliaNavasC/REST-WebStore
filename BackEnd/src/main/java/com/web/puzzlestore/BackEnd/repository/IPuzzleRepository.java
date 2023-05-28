/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.puzzlestore.BackEnd.repository;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface IPuzzleRepository extends JpaRepository<Puzzle, Long>{
    Page<Puzzle> findByNameContainingIgnoreCase(String search,Pageable pageable);
}
