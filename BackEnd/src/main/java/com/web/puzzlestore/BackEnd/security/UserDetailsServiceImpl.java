package com.web.puzzlestore.BackEnd.security;

import java.util.ArrayList;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.Person;
import com.web.puzzlestore.BackEnd.repository.IPersonRepository;
import com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private IPersonRepository personRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username){
        Person userFound = personRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        try {
            List<SimpleGrantedAuthority> roles = getAuthorities(userFound);
            return new org.springframework.security.core.userdetails.User
            (userFound.getUsername(), userFound.getPassword(), roles);
        }
        catch(Exception e){
            throw new UserNotFoundException(username);
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(Person user){
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_"+user.getRol().getName()));
        return roles;
    }
    
}
