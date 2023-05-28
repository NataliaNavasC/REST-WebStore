package com.web.puzzlestore.BackEnd.service.interfaces;

import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.Person;

public interface IPersonService {
    public List<Person> findAll();
    public Person findByUsername(String username);
}