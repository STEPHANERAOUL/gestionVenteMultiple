package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.CartItem;
import com.commerce.model.entity.Product;

public interface CartService {


    public CartItem addCartItem(
            User user ,
            Product product,
            String size,
            int quantity

    );
    public Cart findUserCart(User user);

}
