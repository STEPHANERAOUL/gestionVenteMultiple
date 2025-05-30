package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishList(User user, Product product);



}
