package com.commerce.config.service;

import com.commerce.config.request.LoginRequest;
import com.commerce.model.domain.USER_ROLE;
import com.commerce.reponse.AuthResponse;
import com.commerce.reponse.SignupRequest;
import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest request) ;
   AuthResponse signing(LoginRequest req) throws Exception;


}
