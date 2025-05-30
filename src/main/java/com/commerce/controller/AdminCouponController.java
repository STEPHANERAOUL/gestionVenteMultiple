package com.commerce.controller;


import com.commerce.config.entity.User;
import com.commerce.config.service.UserService;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.Coupon;
import com.commerce.service.CartService;
import com.commerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminCouponController {

    private final CouponService couponService;
    private final CartService cartService;
    private final UserService userService;




    @PostMapping("/apply/coupons")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt

    ) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Cart cart;
        if (apply.equals("true")){
            cart=couponService.applyCoupon(code,orderValue,user);

        }
        else {
            cart= couponService.removeCoupon(code,user);
        }
        return ResponseEntity.ok(cart);



    }
    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
        Coupon createCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createCoupon);

    }
    @DeleteMapping("/admin/delete/{id}")
    public  ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("coupon delet successfully");

    }



@GetMapping("/admin/all")
    public  ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> coupons = couponService.findAllCoupon();
        return ResponseEntity.ok(coupons);

}


}
