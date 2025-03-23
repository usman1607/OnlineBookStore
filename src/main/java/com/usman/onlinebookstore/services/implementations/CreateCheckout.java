package com.usman.onlinebookstore.services.implementations;

import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.models.entities.CartItem;
import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.repositories.CartItemRepository;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.repositories.CheckoutRepository;


@Service
public class CreateCheckout {
    private final CartItemRepository cartItemRepository;
    private final CheckoutRepository checkoutRepository;
    private final CartRepository cartRepository;

    public CreateCheckout(CheckoutRepository checkoutRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.checkoutRepository = checkoutRepository;
        this.cartRepository = cartRepository;
    }

    public Checkout create(Cart cart, PaymentMethod method){
        Checkout checkout = new Checkout(cart.getUserId());
        checkout.setTotalAmount(cart.getItems().stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum());
        checkout.setPaymentMethod(method);
        checkout.setPaymentSuccessful(true);
        checkoutRepository.save(checkout);

        for (CartItem item : cart.getItems()) {
            item.setCheckout(checkout);            
        }
        cartItemRepository.saveAll(cart.getItems());

        cart.getItems().clear();
        cartRepository.save(cart);

        return checkout;
    }
}
