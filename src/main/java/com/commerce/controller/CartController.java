package com.commerce.controller;

import com.commerce.config.entity.User;
import com.commerce.config.request.AddItemRequest;
import com.commerce.config.service.UserService;
import com.commerce.exceptions.ProductException;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.CartItem;
import com.commerce.model.entity.Product;
import com.commerce.reponse.ApiResponse;
import com.commerce.service.CartItemService;
import com.commerce.service.CartService;
import com.commerce.service.ProductService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItmService;
    private final UserService userService;
    private final ProductService productService;



    @GetMapping
    public ResponseEntity<Cart> findUseCarthander(@RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);

    }
    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemtoCart(@RequestBody AddItemRequest req ,
                                                  @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Product products = productService.findProductById(req.getProductId());
        CartItem item =cartService.addCartItem(user,
                products,
                req.getSize(),
                req.getQuantity());
        ApiResponse resp= new ApiResponse();
        resp.setMessage("Item Added To Cart Successfully");
        return new ResponseEntity<>(item,HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHander(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt
    )throws Exception, ExecutionControl.UserException{
        User user = userService.findUserByJwtToken(jwt);
        cartItmService.removeCartItem(user.getId(),cartItemId);
        ApiResponse res=new ApiResponse();
        res.setMessage("Item Remove From Cart");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);


    }
@PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization")String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        CartItem updatedCartItem = null;

        if(cartItem.getQuantity()>8){
            updatedCartItem=cartItmService.updateCartItem(user.getId(),cartItemId,cartItem);
        }
        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);

}






}
