package com.commerce.repository;

import com.commerce.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long sellerId);


}
