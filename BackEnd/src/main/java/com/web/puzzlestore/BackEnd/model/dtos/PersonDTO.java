package com.web.puzzlestore.BackEnd.model.dtos;

public class PersonDTO {
    private Long id;
    private String username;
    private String password;
    private RoleDTO rol;
    public PersonDTO() {
    }
    public PersonDTO(Long id, String username, String password, RoleDTO rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public RoleDTO getRol() {
        return rol;
    }
    public void setRol(RoleDTO rol) {
        this.rol = rol;
    }
    
}
