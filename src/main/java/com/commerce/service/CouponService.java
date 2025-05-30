package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.Coupon;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void  deleteCoupon(Long id) throws Exception;


}
