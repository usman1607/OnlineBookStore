package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.dtos.CartDto;

public interface ICartService {
    CartDto addToCart(String userId, Long bookId, int quantity);
    CartDto viewCart(Long cartId);
} 