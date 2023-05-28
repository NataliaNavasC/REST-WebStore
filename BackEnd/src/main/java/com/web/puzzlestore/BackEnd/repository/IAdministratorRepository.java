/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.puzzlestore.BackEnd.repository;

import com.web.puzzlestore.BackEnd.model.entities.Administrator;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface IAdministratorRepository extends CrudRepository<Administrator, Long>{
    
    public Administrator findByUsername(String username);
}
