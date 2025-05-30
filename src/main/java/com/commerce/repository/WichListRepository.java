package com.commerce.repository;

import com.commerce.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WichListRepository extends JpaRepository<Wishlist,Long> {

    Wishlist findByUserId(Long userId);


}
