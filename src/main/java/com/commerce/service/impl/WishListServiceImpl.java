package com.commerce.service.impl;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Wishlist;
import com.commerce.repository.WichListRepository;
import com.commerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishlistService {

    private final WichListRepository wichListRepository;

    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);

        return wichListRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
      Wishlist wishlist= wichListRepository.findByUserId(user.getId());
      if (wishlist==null){
          wishlist = createWishlist(user);
      }
      return wishlist;
    }

    @Override
    public Wishlist addProductToWishList(User user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);

        return wichListRepository.save(wishlist);
    }
}
