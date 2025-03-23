package com.usman.onlinebookstore.services.implementations;

import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.models.dtos.CartDto;
import com.usman.onlinebookstore.models.dtos.CartItemDto;
import com.usman.onlinebookstore.models.entities.Book;
import com.usman.onlinebookstore.models.entities.Cart;
import com.usman.onlinebookstore.models.entities.CartItem;
import com.usman.onlinebookstore.repositories.BookRepository;
import com.usman.onlinebookstore.repositories.CartRepository;
import com.usman.onlinebookstore.services.interfaces.ICartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(CartRepository cartRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
    }

    public CartDto addToCart(String userId, Long bookId, int quantity) {
        boolean alreadyInCart = false;
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Cart cart = cartRepository.findByUserId(userId);
        if(cart != null){
            for(CartItem item : cart.getItems()){
                if(item.getBook().getId() == bookId){
                    alreadyInCart = true;
                    int qty = item.getQuantity() + quantity;
                    item.setQuantity(qty);
                    break;
                }
            }
        }else{ cart = new Cart(userId); }
        
        if(!alreadyInCart){
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cart.addItem(cartItem);
        }        
        cartRepository.save(cart);

        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUserId());

        List<CartItemDto> itemDtos = cart.getItems().stream().map(item -> {
            CartItemDto dto = new CartItemDto();
            dto.setId(item.getId());
            dto.setBookId(item.getBook().getId());
            dto.setBook(item.getBook().getTitle());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());

        cartDto.setItems(itemDtos);
        return cartDto;
    }

    public CartDto viewCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUserId());

        List<CartItemDto> itemDtos = cart.getItems().stream().map(item -> {
            CartItemDto dto = new CartItemDto();
            dto.setId(item.getId());
            dto.setBookId(item.getBook().getId());
            dto.setBook(item.getBook().getTitle());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());

        cartDto.setItems(itemDtos);
        return cartDto;
    }
}
