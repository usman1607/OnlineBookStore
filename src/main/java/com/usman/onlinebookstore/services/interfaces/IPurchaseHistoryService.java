package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.dtos.CheckoutDto;

import java.util.List;

public interface IPurchaseHistoryService {
    List<CheckoutDto> getPurchaseHistoryByUser(String userId);
}
