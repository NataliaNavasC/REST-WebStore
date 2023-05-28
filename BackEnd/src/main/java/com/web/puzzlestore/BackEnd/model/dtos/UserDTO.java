package com.web.puzzlestore.BackEnd.model.dtos;

import java.util.List;

public class UserDTO extends PersonDTO{
    private List<PurchaseDTO> purchases;
    public UserDTO() {
    }
    public UserDTO(Long id, String username, String password, RoleDTO rol, List<PurchaseDTO> purchases) {
        super(id,username,password,rol);
        this.purchases = purchases;
    }
    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }
    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }
    
}
