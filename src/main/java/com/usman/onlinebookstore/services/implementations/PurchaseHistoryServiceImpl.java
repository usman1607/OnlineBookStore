package com.usman.onlinebookstore.services.implementations;

import org.springframework.stereotype.Service;

import com.usman.onlinebookstore.models.dtos.CartItemDto;
import com.usman.onlinebookstore.models.dtos.CheckoutDto;
import com.usman.onlinebookstore.models.entities.Checkout;
import com.usman.onlinebookstore.repositories.CheckoutRepository;
import com.usman.onlinebookstore.services.interfaces.IPurchaseHistoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService {
    private final CheckoutRepository checkoutRepository;
    
    public PurchaseHistoryServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }
    
    public List<CheckoutDto> getPurchaseHistoryByUser(String userId) {
        List<Checkout> checkouts = checkoutRepository.findByUserIdAndIsSuccess(userId, true);

        List<CheckoutDto> checkoutDtos = new ArrayList<>();
        for (Checkout checkout : checkouts) {
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
            checkoutDtos.add(checkoutDto);
        }

        return checkoutDtos;
    }
}
