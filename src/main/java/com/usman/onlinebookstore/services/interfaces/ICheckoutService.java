package com.usman.onlinebookstore.services.interfaces;

import com.usman.onlinebookstore.models.dtos.CheckoutDto;

public interface ICheckoutService {
    CheckoutDto processCheckout(Long cartId);
}
