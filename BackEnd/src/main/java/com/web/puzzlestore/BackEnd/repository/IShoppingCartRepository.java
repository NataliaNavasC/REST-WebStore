/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.puzzlestore.BackEnd.repository;

import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{
    @Query("Select sc FROM ShoppingCart sc WHERE sc.user.username = :username")
    public ShoppingCart findByUsername(String username);
    public List<ShoppingCart> findAll();
}
