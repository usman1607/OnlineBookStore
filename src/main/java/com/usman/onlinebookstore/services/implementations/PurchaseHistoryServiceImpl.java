package com.usman.onlinebookstore.services.implementations;

import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.repositories.CheckoutRepository;
import com.usman.onlinebookstore.services.interfaces.IPurchaseHistoryService;

import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService {
    private final CheckoutRepository checkoutRepository;
    
    public PurchaseHistoryServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }
    
    public List<Checkout> getPurchaseHistoryByUser(String userId) {
        return checkoutRepository.findByUserIdAndIsSuccess(userId, true);
    }
}
