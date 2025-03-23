package com.usman.onlinebookstore.services.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.models.dtos.CheckoutDto;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.services.interfaces.ICheckoutService;

@Service
@Qualifier("transferCheckoutService")
public class TransferCheckoutService implements ICheckoutService {
    private final CartRepository cartRepository;
    private final CreateCheckout createCheckout;

    public TransferCheckoutService(CartRepository cartRepository, CreateCheckout createCheckout) {
        this.cartRepository = cartRepository;
        this.createCheckout = createCheckout;
    }

    @Override
    public CheckoutDto processCheckout(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        //Process Transfer payment integration here... after successful payment.

        return createCheckout.create(cart, PaymentMethod.TRANSFER);
    }
}
