package com.web.puzzlestore.BackEnd.service.implementations;

import com.web.puzzlestore.BackEnd.model.entities.Role;
import com.web.puzzlestore.BackEnd.repository.IRoleRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
    
}
