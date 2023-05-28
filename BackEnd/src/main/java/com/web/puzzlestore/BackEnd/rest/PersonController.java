package com.web.puzzlestore.BackEnd.rest;

import java.util.ArrayList;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.dtos.PersonDTO;
import com.web.puzzlestore.BackEnd.model.entities.Person;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdminOrUser;
import com.web.puzzlestore.BackEnd.service.interfaces.IPersonService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@IsAdminOrUser
@RequestMapping(
    value = "/persons",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public class PersonController {
    @Autowired
    private IPersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> getAllPersons(){
        List<Person> persons = personService.findAll();
        return convertPersonsToDTOs(persons);
    }

    @RequestMapping(
        value = "{username}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findByUsername(@PathVariable String username){
        Person personFound = personService.findByUsername(username);
        return convertPersonToDTO(personFound);
    }

    private PersonDTO convertPersonToDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }

    private List<PersonDTO> convertPersonsToDTOs(List<Person> persons){
        List<PersonDTO> personsDTOs = new ArrayList<>();
        for (Person person : persons) {
            personsDTOs.add(convertPersonToDTO(person));
        }
        return personsDTOs;
    }
}
