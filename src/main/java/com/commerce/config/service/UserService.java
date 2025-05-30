package com.commerce.config.service;

import com.commerce.config.entity.User;

public interface UserService {

     User findUserByJwtToken(String jwt) throws Exception;
     User findUserByEmail(String email) throws Exception;



}
