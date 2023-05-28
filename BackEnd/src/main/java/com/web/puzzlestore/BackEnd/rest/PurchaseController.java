package com.web.puzzlestore.BackEnd.rest;

import com.web.puzzlestore.BackEnd.model.dtos.PurchaseDTO;
import com.web.puzzlestore.BackEnd.model.dtos.Response.IntegerResponseDTO;
import com.web.puzzlestore.BackEnd.model.entities.Purchase;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdmin;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdminOrUser;
import com.web.puzzlestore.BackEnd.security.Roles.IsUser;
import com.web.puzzlestore.BackEnd.service.interfaces.IPurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@IsAdmin
@RequestMapping(
    value = "/purchases"
)
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @Autowired
    private ModelMapper modelMapper;

    @IsAdminOrUser
    @RequestMapping(
        value = "{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PurchaseDTO findPurchseByID(@PathVariable long id){
        Purchase purchase = purchaseService.findPurchaseById(id);
        return convertPurchaseToDTO(purchase);
    }

    @IsUser
    @RequestMapping(
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public PurchaseDTO createPuchase(@RequestBody PurchaseDTO purchaseDTO){
        Purchase purchase = purchaseService.createPurchase(convertDTOToPurchase(purchaseDTO));
        return convertPurchaseToDTO(purchase);
    }

    @RequestMapping(
        value = "month-report/{page}/{size}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<PurchaseDTO> getLastMonthReport(@PathVariable int page, @PathVariable int size){
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<Purchase> purchases = purchaseService.getLastMonthReport(pageable);
        List<PurchaseDTO> purchasesDTOs = convertPurchasesToDTOs(purchases);
        return new PageImpl<>(purchasesDTOs, pageable, purchases.getTotalElements());
    }

    @RequestMapping(
        value = "top-purchases-report/{page}/{size}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<PurchaseDTO> getTopPurchasesReport(@PathVariable int page, @PathVariable int size){
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Sort.Direction.DESC, "total"));
        Page<Purchase> purchases = purchaseService.getTopPurchasesReport(pageable);
        List<PurchaseDTO> purchasesDTOs = convertPurchasesToDTOs(purchases);
        return new PageImpl<>(purchasesDTOs, pageable, purchases.getTotalElements());
    }

    @IsAdminOrUser
    @RequestMapping(
        value = "history/{page}/{size}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<PurchaseDTO> getPurchases(@PathVariable int page, @PathVariable int size){
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Sort.Direction.DESC, "date"));
        Page<Purchase> purchases = purchaseService.getTopPurchasesReport(pageable);
        List<PurchaseDTO> purchasesDTOs = convertPurchasesToDTOs(purchases);
        return new PageImpl<>(purchasesDTOs, pageable, purchases.getTotalElements());
    }

    @RequestMapping(
        value = "profits/{filter}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public IntegerResponseDTO getProfits(@PathVariable String filter){
        int profits = purchaseService.getProfits(filter);
        return new IntegerResponseDTO(profits);
    }

    //================= CONVERTES =================//

    private PurchaseDTO convertPurchaseToDTO(Purchase purchase){
        return modelMapper.map(purchase, PurchaseDTO.class);
    }

    private Purchase convertDTOToPurchase(PurchaseDTO purchaseDTO){
        return modelMapper.map(purchaseDTO, Purchase.class);
    }

    private List<PurchaseDTO> convertPurchasesToDTOs(Page<Purchase> purchasePage){
        List<PurchaseDTO> purchaseDTOs = new ArrayList<PurchaseDTO>();
        for (Purchase purchase : purchasePage) {
            purchaseDTOs.add(convertPurchaseToDTO(purchase));
        }
        return purchaseDTOs;
    }
}
