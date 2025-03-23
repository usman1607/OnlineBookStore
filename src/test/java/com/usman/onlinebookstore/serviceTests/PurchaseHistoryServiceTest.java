package com.usman.onlinebookstore.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.repositories.CheckoutRepository;
import com.usman.onlinebookstore.services.implementations.PurchaseHistoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PurchaseHistoryServiceTest {
    @Mock
    private CheckoutRepository checkoutRepository;
    @InjectMocks
    private PurchaseHistoryServiceImpl purchaseHistoryService;

    private Checkout checkout;

    @BeforeEach
    void setUp() {
        checkout = new Checkout("TestUser");
        checkout.setId(1L);
    }

    @Test
    void testGetPurchaseHistory() {
        when(checkoutRepository.findByUserIdAndIsSuccess("TestUser", true)).thenReturn(Arrays.asList(checkout));
        List<Checkout> history = purchaseHistoryService.getPurchaseHistoryByUser("TestUser");
        assertFalse(history.isEmpty());
        assertEquals(1, history.size());
        assertEquals("TestUser", history.get(0).getUserId());
    }

    @Test
    void testGetPurchaseHistoryForNonExistentUser() {
        when(checkoutRepository.findByUserIdAndIsSuccess("TestUser2", true)).thenReturn(Arrays.asList());
        List<Checkout> history = purchaseHistoryService.getPurchaseHistoryByUser("TestUser2");
        assertTrue(history.isEmpty());
    }
}
