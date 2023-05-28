package com.web.puzzlestore.BackEnd.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.model.entities.PuzzleOrder;
import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;
import com.web.puzzlestore.BackEnd.repository.IShoppingCartRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IPuzzleOrderService;
import com.web.puzzlestore.BackEnd.service.interfaces.IShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IPuzzleOrderService puzzleOrderService;

    @Override
    public ShoppingCart getShoppingCartByUsername(String username) {
        return this.shoppingCartRepository.findByUsername(username);
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCartToCreate) {
        return this.shoppingCartRepository.save(shoppingCartToCreate);
    }

    @Override
    public ShoppingCart cleanShoppingCart(String username) {
        ShoppingCart shoppingCart = this.getShoppingCartByUsername(username);
        shoppingCart.setPuzzleOrders(new ArrayList<>());
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart addToPuzzleOrder(Puzzle puzzleToAdd, String username) {
        ShoppingCart shoppingCart = this.getShoppingCartByUsername(username);
        PuzzleOrder found = null;
        for (PuzzleOrder puzzleOrder : shoppingCart.getPuzzleOrders()) {
            if(puzzleToAdd.getId() == puzzleOrder.getPuzzle().getId()){
                puzzleOrder.setCount(puzzleOrder.getCount()+1);
                found = puzzleOrderService.updatePuzzleOrder(puzzleOrder);
            }
        }
        if(found == null){
            found = new PuzzleOrder();
            found.setPuzzle(puzzleToAdd);
            found.setCount(1);
            this.puzzleOrderService.creatPuzzleOrder(found);
            shoppingCart.addPuzzleOrder(found);
        }
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart removePuzzleOrder(long puzzleIdToRemove, String username) {
        ShoppingCart shoppingCart = this.getShoppingCartByUsername(username);
        PuzzleOrder found = null;
        for (PuzzleOrder puzzleOrder : shoppingCart.getPuzzleOrders()) {
            if(puzzleIdToRemove == puzzleOrder.getId()){
                found = puzzleOrder;
            }
        }
        if(found != null){
            puzzleOrderService.deletePuzzleOrder(found.getId());
            shoppingCart.removePuzzleOrder(found);
        }
        return this.shoppingCartRepository.save(shoppingCart);
    }
    
}
