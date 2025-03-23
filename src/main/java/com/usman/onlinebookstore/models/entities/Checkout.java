package com.usman.onlinebookstore.models.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
import java.util.ArrayList;

import com.usman.onlinebookstore.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "checkout")
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private double totalAmount;
    
    private PaymentMethod paymentMethod;
    private boolean isSuccess;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> purchasedItems = new ArrayList<>();

    public Checkout(String userId) {
        this.userId = userId;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId(){ return userId; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public boolean isPaymentSuccessful() { return isSuccess; }
    public void setPaymentSuccessful(boolean isSuccess) { this.isSuccess = isSuccess; }
    public List<CartItem> getItems() { return purchasedItems; }
    public void setItems(List<CartItem> items) { this.purchasedItems = items; }
    public double getTotalAmount(){ return totalAmount; }
    public void setTotalAmount(double amount) { this.totalAmount = amount; }
}
