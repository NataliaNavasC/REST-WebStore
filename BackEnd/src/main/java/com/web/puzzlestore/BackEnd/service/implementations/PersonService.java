package com.web.puzzlestore.BackEnd.service.implementations;

import java.util.List;
import java.util.Optional;

import com.web.puzzlestore.BackEnd.model.entities.Person;
import com.web.puzzlestore.BackEnd.repository.IPersonRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IPersonService;
import com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByUsername(String username) {
        Optional<Person> personFound = this.personRepository.findByUsername(username);
        if(personFound.get() == null){
            throw new UserNotFoundException(username);
        }
        return personFound.get();
    }
    
}