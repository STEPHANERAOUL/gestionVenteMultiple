package com.commerce.config.request;

import com.commerce.model.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequet {
   private String email;
  private  String otp;
   private USER_ROLE role;
}
