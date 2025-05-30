package com.commerce.repository;

import com.commerce.model.domain.AccountStatus;
import com.commerce.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface SellerRepository extends JpaRepository<Seller ,Long> {


    List<Seller>  findByAccountStatus(AccountStatus status);

    Seller findByEmail(String email);
}
