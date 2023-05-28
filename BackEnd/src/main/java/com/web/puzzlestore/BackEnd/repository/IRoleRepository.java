package com.web.puzzlestore.BackEnd.repository;


import com.web.puzzlestore.BackEnd.model.entities.Role;

import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    public Role findByName(String name);
}
