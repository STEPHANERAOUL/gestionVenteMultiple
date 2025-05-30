package com.commerce.repository;

import com.commerce.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart ,Long> {

    Cart findByUserId(Long id);

}
