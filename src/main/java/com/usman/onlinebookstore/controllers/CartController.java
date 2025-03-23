package com.usman.onlinebookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usman.onlinebookstore.models.dtos.CartDto;
import com.usman.onlinebookstore.services.implementations.CartServiceImpl;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartDto addToCart(@RequestParam String userId, @RequestParam Long bookId, @RequestParam int quantity) {
        return cartService.addToCart(userId, bookId, quantity);
    }

    @GetMapping("/{cartId}")
    public CartDto viewCart(@PathVariable Long cartId) {
        return cartService.viewCart(cartId);
    }
}
