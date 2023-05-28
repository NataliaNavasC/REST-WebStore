package com.web.puzzlestore.BackEnd.service.interfaces;

import com.web.puzzlestore.BackEnd.model.entities.Role;

public interface IRoleService {
    public Role findByName(String name);
}
