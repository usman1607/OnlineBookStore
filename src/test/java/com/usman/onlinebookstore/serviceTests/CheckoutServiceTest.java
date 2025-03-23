package com.usman.onlinebookstore.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import com.usman.onlinebookstore.enums.PaymentMethod;
import com.usman.onlinebookstore.models.dtos.CheckoutDto;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.models.entities.CartItem;
import com.usman.onlinebookstore.repositories.CartItemRepository;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.repositories.CheckoutRepository;
import com.usman.onlinebookstore.services.implementations.CreateCheckout;
import com.usman.onlinebookstore.services.implementations.TransferCheckoutService;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {
    @Mock
    private CheckoutRepository checkoutRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private CreateCheckout createCheckout;
    @InjectMocks
    private TransferCheckoutService transferCheckoutService;

    private Cart cart;
    private CartItem cartItem;
    private Book book;

    @BeforeEach
    void setUp() {
        cart = new Cart("TestUser");
        cart.setId(1L);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setPrice(10.0);
        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setBook(book);
        cartItem.setQuantity(2);
        cart.getItems().add(cartItem);
    }

    @Test
    void testProcessCheckout() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(createCheckout.create(cart, PaymentMethod.TRANSFER)).thenReturn(new CheckoutDto());

        CheckoutDto result = transferCheckoutService.processCheckout(1L);
        assertNotNull(result);
    }

    @Test
    void testProcessCheckoutWithEmptyCart() {
        Cart emptyCart = new Cart("TestUser");
        emptyCart.setId(2L);
        when(cartRepository.findById(2L)).thenReturn(Optional.of(emptyCart));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transferCheckoutService.processCheckout(2L);
        });

        assertEquals("Cart is empty", exception.getMessage());
    }

    @Test
    void testProcessCheckoutWithNonExistentCart() {
        when(cartRepository.findById(3L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transferCheckoutService.processCheckout(3L);
        });

        assertEquals("Cart not found", exception.getMessage());
    }
}
