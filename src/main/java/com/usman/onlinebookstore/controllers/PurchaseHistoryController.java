package com.usman.onlinebookstore.controllers;

import org.springframework.web.bind.annotation.*;

import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.services.implementations.PurchaseHistoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-history")
public class PurchaseHistoryController {
    private final PurchaseHistoryServiceImpl purchaseHistoryService;
    
    public PurchaseHistoryController(PurchaseHistoryServiceImpl purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }
    
    @GetMapping("/{userId}")
    public List<Checkout> getPurchaseHistory(@PathVariable String userId) {
        return purchaseHistoryService.getPurchaseHistoryByUser(userId);
    }
}
