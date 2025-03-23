package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.entities.Checkout;

import java.util.List;

public interface IPurchaseHistoryService {
    List<Checkout> getPurchaseHistoryByUser(String userId);
}
