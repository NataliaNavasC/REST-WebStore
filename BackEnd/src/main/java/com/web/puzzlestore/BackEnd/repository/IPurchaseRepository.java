/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.puzzlestore.BackEnd.repository;

import com.web.puzzlestore.BackEnd.model.entities.Purchase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author User
 */
public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{
    Page<Purchase> findByDateAfter(LocalDateTime date, Pageable pageable);
    List<Purchase> findByDateAfter(LocalDateTime date);
    Page<Purchase> findAll(Pageable pageable);
}
