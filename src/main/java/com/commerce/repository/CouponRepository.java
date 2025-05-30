package com.commerce.repository;

import com.commerce.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon , Long> {


    Coupon findByCode(String code);
}
