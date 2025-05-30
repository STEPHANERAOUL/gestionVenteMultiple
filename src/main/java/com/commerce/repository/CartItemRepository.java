package com.commerce.repository;

import com.commerce.model.entity.Cart;
import com.commerce.model.entity.CartItem;
import com.commerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
