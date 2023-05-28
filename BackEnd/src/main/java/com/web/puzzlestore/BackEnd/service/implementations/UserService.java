package com.web.puzzlestore.BackEnd.service.implementations;


import com.web.puzzlestore.BackEnd.model.entities.Role;
import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;
import com.web.puzzlestore.BackEnd.model.entities.User;
import com.web.puzzlestore.BackEnd.repository.IUserRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IRoleService;
import com.web.puzzlestore.BackEnd.service.interfaces.IShoppingCartService;
import com.web.puzzlestore.BackEnd.service.interfaces.IUserService;
import com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions.UserNotFoundException;
import com.web.puzzlestore.BackEnd.utils.exceptions.UserExceptions.UsernameAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User registerUser(User newUser) {
        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            Role userRole = this.roleService.findByName("USER");
            newUser.setRol(userRole);
            User createdUser = this.userRepository.save(newUser);
            ShoppingCart newShoppingCart = new ShoppingCart();
            newShoppingCart.setUser(createdUser);
            this.shoppingCartService.createShoppingCart(newShoppingCart);
            return createdUser;
        }
        catch(Exception ex){
            throw new UsernameAlreadyExistsException(newUser.getUsername());
        }
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }

    @Override
    public User updateUser(User user) {
        User userFound = this.findByUsername(user.getUsername());
        userFound.setPurchases(user.getPurchases());
        return this.userRepository.save(userFound);
    }

    
}
