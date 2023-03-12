package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.Purchase;
import com.example.shoppinglist.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${purchase-controller.version}")
@CrossOrigin
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @RequestMapping(value = "/purchase", method= RequestMethod.POST)
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @RequestMapping(value = "/purchase", method= RequestMethod.GET)
    public List<Purchase> readPurchase() {
        return purchaseService.getPurchases();
    }

    @RequestMapping(value = "/purchase/{purchaseId}", method= RequestMethod.PUT)
    public Purchase updatePurchase(@PathVariable(value = "purchaseId") Long id, @RequestBody Purchase purchaseDetails) {
        return purchaseService.updatePurchase(id, purchaseDetails);
    }

    @RequestMapping(value = "/purchase/{purchaseId}", method= RequestMethod.DELETE)
    public void deletePurchase(@PathVariable(value = "purchaseId") Long id) {
        purchaseService.deletePurchase(id);
    }
}
