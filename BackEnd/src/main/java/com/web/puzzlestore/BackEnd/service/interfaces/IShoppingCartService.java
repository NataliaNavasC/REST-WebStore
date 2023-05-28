package com.web.puzzlestore.BackEnd.service.interfaces;

import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;

public interface IShoppingCartService {
    public ShoppingCart getShoppingCartByUsername(String username);
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCartToCreate);
    public ShoppingCart addToPuzzleOrder(Puzzle puzzleToAdd, String username);
    public ShoppingCart removePuzzleOrder(long puzzleIdToRemove, String username);
    public ShoppingCart cleanShoppingCart(String username);
    public List<ShoppingCart> getAllShoppingCarts();
}
