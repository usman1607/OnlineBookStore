package com.usman.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usman.onlinebookstore.models.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>  {}
