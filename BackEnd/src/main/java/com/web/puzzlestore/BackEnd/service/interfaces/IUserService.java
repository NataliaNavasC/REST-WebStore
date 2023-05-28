package com.web.puzzlestore.BackEnd.service.interfaces;

import com.web.puzzlestore.BackEnd.model.entities.User;

public interface IUserService {
    public User registerUser(User newUser);
    public User findByUsername(String username);
    public User updateUser(User user);
}
