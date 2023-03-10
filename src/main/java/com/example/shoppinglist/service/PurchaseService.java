package com.example.shoppinglist.service;

import com.example.shoppinglist.exception.ResourceNotFoundException;
import com.example.shoppinglist.model.Purchase;
import com.example.shoppinglist.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    /**
     * CREATE
     * @param purchase
     * @return
     */
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    /**
     * READ
     * @return
     */
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    /**
     * UPDATE
     * @param purchaseId
     * @param purchaseDetails
     * @return
     */
    public Purchase updatePurchase(Long purchaseId, Purchase purchaseDetails) {
        Optional<Purchase> purchaseOpt = purchaseRepository.findById(purchaseId);
        if (purchaseOpt.isPresent()) {
            Purchase purchase = purchaseOpt.get();
            purchase.setTitle(purchaseDetails.getTitle());
            purchase.setDescription(purchaseDetails.getDescription());
            purchase.setState(purchaseDetails.getState());

            return purchaseRepository.save(purchase);
        } else {
            throw new ResourceNotFoundException("Purchase", "Id", purchaseId);
        }
    }

    /**
     * DELETE
     * @param purchaseId
     */
    public void deletePurchase(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}
