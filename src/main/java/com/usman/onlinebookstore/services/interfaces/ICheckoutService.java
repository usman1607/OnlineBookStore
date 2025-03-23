package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.entities.Checkout;

public interface ICheckoutService {
    Checkout processCheckout(Long cartId);
}
