package com.usman.onlinebookstore.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.models.dtos.CartItemDto;
import com.usman.onlinebookstore.models.dtos.CheckoutDto;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.models.entities.CartItem;
import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.repositories.CheckoutRepository;


@Service
public class CreateCheckout {
    private final CheckoutRepository checkoutRepository;
    private final CartRepository cartRepository;

    public CreateCheckout(CheckoutRepository checkoutRepository, CartRepository cartRepository) {
        this.checkoutRepository = checkoutRepository;
        this.cartRepository = cartRepository;
    }

    public CheckoutDto create(Cart cart, PaymentMethod method){
        Checkout checkout = new Checkout(cart.getUserId());
        checkout.setTotalAmount(cart.getItems().stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum());
        checkout.setPaymentMethod(method);
        checkout.setPaymentSuccessful(true);
        

        for (CartItem item : cart.getItems()) {
            var cItem = new CartItem();
            //cItem.setId(item.getId());
            cItem.setBook(item.getBook());
            cItem.setCheckout(checkout);
            cItem.setQuantity(item.getQuantity());
            checkout.addItems(cItem);
        }
        checkoutRepository.save(checkout);
        //cartItemRepository.saveAll(cart.getItems());

        cart.getItems().clear();
        cartRepository.save(cart);

        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setId(checkout.getId());
        checkoutDto.setUserId(checkout.getUserId());
        checkoutDto.setPaymentMethod(checkout.getPaymentMethod());
        checkoutDto.setSuccess(checkout.isPaymentSuccessful());
        checkoutDto.setTotalAmount(checkout.getTotalAmount());

        List<CartItemDto> itemDtos = checkout.getItems().stream().map(item -> {
            CartItemDto dto = new CartItemDto();
            dto.setId(item.getId());
            dto.setBookId(item.getBook().getId());
            dto.setBook(item.getBook().getTitle());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());

        checkoutDto.setItems(itemDtos);

        return checkoutDto;
    }
}
