package com.commerce.service;

import com.commerce.exceptions.SellerException;
import com.commerce.model.domain.AccountStatus;
import com.commerce.model.entity.Seller;

import java.util.List;

public interface SellerService {
   Seller getSellerProfile(String jwt) throws Exception;
   Seller createSeller(Seller seller) throws Exception;
   Seller getSellerById(Long id) throws SellerException;
   Seller getSellerByEmail(String email) throws Exception;
   List<Seller> getAllSeller(AccountStatus accountStatus);
   Seller updateSeller(Long id,Seller seller) throws Exception;
   void deleteseller(Long id) throws Exception;
   Seller verifyEmail(String email,String otp) throws Exception;
   Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;


}
