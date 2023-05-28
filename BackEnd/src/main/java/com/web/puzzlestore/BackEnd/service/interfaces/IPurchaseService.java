package com.web.puzzlestore.BackEnd.service.interfaces;

import com.web.puzzlestore.BackEnd.model.entities.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPurchaseService{
    
    public Purchase createPurchase(Purchase purchaseToCreate);
    
    public Page<Purchase> getLastMonthReport(Pageable pageable);

    public Page<Purchase> getTopPurchasesReport(Pageable pageable);

    public Page<Purchase> findAll(Pageable pageable);

    public Purchase findPurchaseById(long id);

    public int getProfits(String filter);
}
