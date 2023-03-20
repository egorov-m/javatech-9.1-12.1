package com.example.shoppinglist;

import com.example.shoppinglist.model.Purchase;
import com.example.shoppinglist.model.PurchaseState;
import com.example.shoppinglist.repository.PurchaseRepository;
import com.example.shoppinglist.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PurchaseServiceTests {
    @Autowired
    private PurchaseService purchaseService;
    @MockBean
    private PurchaseRepository purchaseRepository;

    @Test
    public void addPurchaseTest() {
        Purchase purchase = new Purchase();
        purchase.setTitle("Test purchase");
        purchase.setDescription("Test purchase description");
        purchase.setState(PurchaseState.PURCHASED);

        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        Purchase result = purchaseService.createPurchase(purchase);
        verify(purchaseRepository).save(purchase);
        assertThat(result).isNotNull();
    }

    @Test
    public void getAllPurchasesTest() {
        List<Purchase> purchases = new ArrayList<Purchase>();
        Purchase purchase1 = new Purchase();
        purchase1.setTitle("Purchase 1");
        purchase1.setDescription("Description 1");
        purchase1.setState(PurchaseState.PURCHASED);

        Purchase purchase2 = new Purchase();
        purchase2.setTitle("Purchase 2");
        purchase2.setDescription("Description 2");
        purchase2.setState(PurchaseState.SELECTED);

        Purchase purchase3 = new Purchase();
        purchase3.setTitle("Purchase 3");
        purchase3.setDescription("Description 3");
        purchase3.setState(PurchaseState.PURCHASED);

        purchases.add(purchase1);
        purchases.add(purchase2);
        purchases.add(purchase3);

        when(purchaseService.getPurchases()).thenReturn(purchases);
        List<Purchase> listResult = purchaseService.getPurchases();
        verify(purchaseRepository).findAll();
        assertThat(listResult).isNotNull();
        assertThat(listResult.size()).isEqualTo(purchases.size());
        for (int i = 0; i < listResult.size(); i++) {
            assertThat(listResult.get(i).getTitle()).isEqualTo(purchases.get(i).getTitle());
        }
    }
}
