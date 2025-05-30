package com.commerce.service;


import com.commerce.model.entity.CartItem;

public interface CartItemService {

    CartItem updateCartItem(Long userId, Long id , CartItem cartItem) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItem findCartItemById(Long id) throws Exception;





}
