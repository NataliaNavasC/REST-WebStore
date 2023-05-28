package com.web.puzzlestore.BackEnd.rest;

import java.util.ArrayList;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.dtos.PuzzleDTO;
import com.web.puzzlestore.BackEnd.model.dtos.ShoppingCartDTO;
import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.model.entities.ShoppingCart;
import com.web.puzzlestore.BackEnd.security.Roles.IsUser;
import com.web.puzzlestore.BackEnd.service.interfaces.IShoppingCartService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@IsUser
@RequestMapping(
    value = "/shoppingcart",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(
        value = "{username}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ShoppingCartDTO getShoppingCartByUsername(@PathVariable String username){
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUsername(username);
        return convertShoppingCartToDTO(shoppingCart);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ShoppingCartDTO> getAllShoppingCartDTOs(){
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return convertShoppingCartsToDTOs(shoppingCarts);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ShoppingCartDTO createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartToCreate){
        ShoppingCart shoppingCart = this.shoppingCartService.createShoppingCart(convertDTOToShoppingCart(shoppingCartToCreate));
        return convertShoppingCartToDTO(shoppingCart);
    }

    @RequestMapping(
        value = "{username}/orders",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ShoppingCartDTO addToPuzzleOrder(@RequestBody PuzzleDTO puzzleDTO, @PathVariable String username){
        ShoppingCart shoppingCart = this.shoppingCartService.addToPuzzleOrder(convertDTOToPuzzle(puzzleDTO), username);
        return convertShoppingCartToDTO(shoppingCart);
    }

    @RequestMapping(
        value = "{username}/orders/{orderId}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ShoppingCartDTO removePuzzleOrder(@PathVariable String username, @PathVariable long orderId){
        ShoppingCart shoppingCart = this.shoppingCartService.removePuzzleOrder(orderId, username);
        return convertShoppingCartToDTO(shoppingCart);
    }
    
    @RequestMapping(
        value = "{username}/orders",
        method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ShoppingCartDTO cleanShoppingCart(@PathVariable String username){
        ShoppingCart shoppingCart = this.shoppingCartService.cleanShoppingCart(username);
        return convertShoppingCartToDTO(shoppingCart);
    }

    private ShoppingCartDTO convertShoppingCartToDTO(ShoppingCart shoppingCart){
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    private ShoppingCart convertDTOToShoppingCart(ShoppingCartDTO shoppingCartDTO){
        return modelMapper.map(shoppingCartDTO, ShoppingCart.class);
    }

    private List<ShoppingCartDTO> convertShoppingCartsToDTOs(List<ShoppingCart> shoppingCarts){
        List<ShoppingCartDTO> cartDTOs = new ArrayList<>();
        for (ShoppingCart cart : shoppingCarts) {
            cartDTOs.add(convertShoppingCartToDTO(cart));
        }
        return cartDTOs;
    }
    private Puzzle convertDTOToPuzzle(PuzzleDTO puzzleDTO){
        return modelMapper.map(puzzleDTO, Puzzle.class);
    }
}
