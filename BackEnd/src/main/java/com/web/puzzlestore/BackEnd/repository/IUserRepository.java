/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.puzzlestore.BackEnd.repository;

import java.util.Optional;

import com.web.puzzlestore.BackEnd.model.entities.User;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface IUserRepository extends CrudRepository<User, Long>{
    public Optional<User> findByUsername(String username);
}
