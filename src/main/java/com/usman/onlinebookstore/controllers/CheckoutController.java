package com.usman.onlinebookstore.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.services.CheckoutServiceFactory;
import com.usman.onlinebookstore.services.interfaces.ICheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutServiceFactory checkoutServiceFactory;

    public CheckoutController(CheckoutServiceFactory checkoutServiceFactory) {
        this.checkoutServiceFactory = checkoutServiceFactory;
    }

    @PostMapping("/process")
    public Checkout processCheckout(@RequestParam Long cartId, @RequestParam PaymentMethod paymentMethod) {
        ICheckoutService service = checkoutServiceFactory.getCheckoutService(paymentMethod);
        return service.processCheckout(cartId);
    }
}
