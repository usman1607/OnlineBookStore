package com.usman.onlinebookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usman.onlinebookstore.models.entities.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findByUserIdAndIsSuccess(String userId, boolean isSuccess);
}