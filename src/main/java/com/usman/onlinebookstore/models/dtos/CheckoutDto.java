package com.usman.onlinebookstore.models.dtos;

import java.util.ArrayList;
import java.util.List;

import com.usman.onlinebookstore.enums.PaymentMethod;

public class CheckoutDto {
    private Long id;
    private String userId;
    private double totalAmount;    
    private PaymentMethod paymentMethod;
    private boolean isSuccess;
    private List<CartItemDto> items = new ArrayList<>();

    public CheckoutDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
    
}
