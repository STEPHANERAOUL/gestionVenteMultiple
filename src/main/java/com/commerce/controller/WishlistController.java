package com.commerce.controller;

import com.commerce.config.entity.User;
import com.commerce.config.service.UserService;
import com.commerce.exceptions.ProductException;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Wishlist;
import com.commerce.service.ProductService;
import com.commerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Wishlist")
public class WishlistController {


    private final WishlistService wishlistService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(
            @RequestBody User user

            ){
        Wishlist wishlist = wishlistService.createWishlist(user);
        return ResponseEntity.ok(wishlist);


    }
    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUsserId(
            @RequestHeader("Authorization") String jwt

    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Wishlist wishlist =wishlistService.getWishlistByUserId(user);

        return ResponseEntity.ok(wishlist);
    }
@PostMapping("/add-product/{productId}")
public ResponseEntity<Wishlist> addProductToWishlist(
        @PathVariable Long productId ,
        @RequestHeader("Authorization") String jwt

) throws Exception {
    Product product =productService.findProductById(productId);
    User user = userService.findUserByJwtToken(jwt);
    Wishlist wishlist= wishlistService.addProductToWishList(
            user, product

    );
    return ResponseEntity.ok(wishlist);

}

}
