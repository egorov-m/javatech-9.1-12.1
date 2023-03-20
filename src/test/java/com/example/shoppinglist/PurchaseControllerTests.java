package com.example.shoppinglist;

import com.example.shoppinglist.model.Purchase;
import com.example.shoppinglist.model.PurchaseState;
import com.example.shoppinglist.repository.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseRepository purchaseRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addPurchaseTest() throws Exception {
        Purchase purchase1 = new Purchase();
        purchase1.setTitle("Purchase 1");
        purchase1.setDescription("Description 1");
        purchase1.setState(PurchaseState.PURCHASED);

        mockMvc.perform(post("/api/v1/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPurchasesTest() throws Exception {
        mockMvc.perform(get("/api/v1/purchase"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
