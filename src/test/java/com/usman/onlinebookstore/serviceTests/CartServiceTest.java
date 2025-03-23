package com.usman.onlinebookstore.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usman.onlinebookstore.models.dtos.CartDto;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.repositories.BookRepository;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.services.implementations.CartServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;
    private Book book;

    @BeforeEach
    void setUp() {
        cart = new Cart("TestUser");
        cart.setId(1L);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setPrice(10.0);
    }

    @Test
    void testAddItemToCart() {
        when(cartRepository.findByUserId("TestUser")).thenReturn(cart);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        CartDto dto = cartService.addToCart("TestUser", 1L, 2);
        assertEquals("TestUser", dto.getUserId());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test
    void testViewCart() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        CartDto retrievedCart = cartService.viewCart(1L);
        assertTrue(retrievedCart != null);
        assertEquals("TestUser", retrievedCart.getUserId());
        assertEquals(1L, retrievedCart.getId());
    }
}
