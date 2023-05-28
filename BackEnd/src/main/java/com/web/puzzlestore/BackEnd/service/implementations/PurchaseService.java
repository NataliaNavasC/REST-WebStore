package com.web.puzzlestore.BackEnd.service.implementations;

import com.web.puzzlestore.BackEnd.model.entities.Purchase;
import com.web.puzzlestore.BackEnd.model.entities.User;
import com.web.puzzlestore.BackEnd.repository.IPurchaseRepository;
import com.web.puzzlestore.BackEnd.service.interfaces.IPurchaseService;
import com.web.puzzlestore.BackEnd.service.interfaces.IShoppingCartService;
import com.web.puzzlestore.BackEnd.service.interfaces.IUserService;
import com.web.puzzlestore.BackEnd.utils.exceptions.PurchaseExceptions.PurchaseNotFoundException;

import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IUserService userService;

    @Override
    public Page<Purchase> getLastMonthReport(Pageable pageable) {
        LocalDateTime dateAMonthAgo = LocalDateTime.now().minus(Period.ofDays(30));
        return this.purchaseRepository.findByDateAfter(dateAMonthAgo,pageable);
    }

    @Override
    public Page<Purchase> getTopPurchasesReport(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable);
    }

    @Override
    public Page<Purchase> findAll(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable);
    }

    @Override
    public Purchase findPurchaseById(long id) {
        return this.purchaseRepository.findById(id).orElseThrow(()-> new PurchaseNotFoundException(id));
    }

    @Override
    public int getProfits(String filter) {
        int profits = 0;
        List<Purchase> purchases = new ArrayList<Purchase>();
        switch(filter){
            case "last-month":
                LocalDateTime dateAMonthAgo = LocalDateTime.now().minus(Period.ofDays(30));
                purchases = this.purchaseRepository.findByDateAfter(dateAMonthAgo);
                break;
            case "top-five":
                purchases = findTopPurchases(5);
                break;
            case "top-ten":
                purchases = findTopPurchases(10);
                break;
            default:
                purchases = this.purchaseRepository.findAll();
                break;
        }
         
        for (Purchase purchase : purchases) {
            profits += purchase.getTotal();
        }
        return profits;
    }

    private List<Purchase> findTopPurchases(int size){
        List<Purchase> purchases = new ArrayList<Purchase>();
        Pageable pageable = new SolrPageRequest(0, size, Sort.by(Sort.Direction.DESC, "total"));
        Page<Purchase> page = this.purchaseRepository.findAll(pageable);
        for(Purchase purchase: page){
            purchases.add(purchase);
        }
        return purchases;
    }

    @Override
    public Purchase createPurchase(Purchase purchaseToCreate) {
        this.shoppingCartService.cleanShoppingCart(purchaseToCreate.getUsername());
        Purchase purchase = this.purchaseRepository.save(purchaseToCreate);
        User user = this.userService.findByUsername(purchase.getUsername());
        user.addPurchase(purchase);
        this.userService.updateUser(user);
        return purchase;
    }
}
