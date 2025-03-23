package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.entities.Cart;

public interface ICartService {
    Cart addToCart(String userId, Long bookId, int quantity);
    Cart viewCart(Long cartId);
} 