package com.web.puzzlestore.BackEnd.model.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author User
 */
@Entity
public class User extends Person {

    @OneToMany(fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    public List<Purchase> getPurchases() {
        if (purchases == null) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(Purchase purchase) {
        getPurchases().add(purchase);
    }

    public void removePurchase(Purchase purchase) {
        getPurchases().remove(purchase);
    }

}