package com.web.puzzlestore.BackEnd.rest;

import com.web.puzzlestore.BackEnd.model.dtos.UserDTO;
import com.web.puzzlestore.BackEnd.model.entities.User;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdminOrUser;
import com.web.puzzlestore.BackEnd.service.interfaces.IUserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/users",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //@IsAdminOrUser
    @RequestMapping(
        value = "{username}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserDTO findByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        return convertUserToDTO(user);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserDTO registerUser(@RequestBody UserDTO userToCreate){
        User user = userService.registerUser(convertDTOToUser(userToCreate));
        return convertUserToDTO(user);
    }

    private UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
