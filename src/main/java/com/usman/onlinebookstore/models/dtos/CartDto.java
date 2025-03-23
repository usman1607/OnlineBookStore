package com.usman.onlinebookstore.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long id;
    private String userId;
    private List<CartItemDto> items = new ArrayList<>();

    public CartDto(){}

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

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}
