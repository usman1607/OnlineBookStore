package com.usman.onlinebookstore.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.services.interfaces.ICheckoutService;

import java.util.Map;

@Component
public class CheckoutServiceFactory {
    private final Map<String, ICheckoutService> checkoutServices;

    public CheckoutServiceFactory(
        @Qualifier("webCheckoutService") ICheckoutService webCheckoutService,
        @Qualifier("ussdCheckoutService") ICheckoutService ussdCheckoutService,
        @Qualifier("transferCheckoutService") ICheckoutService transferCheckoutService
    ) {
        this.checkoutServices = Map.of(
            "WEB", webCheckoutService,
            "USSD", ussdCheckoutService,
            "TRANSFER", transferCheckoutService
        );
    }

    public ICheckoutService getCheckoutService(PaymentMethod method) {
        return checkoutServices.get(method.name());
    }
}
