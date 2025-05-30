package com.commerce.config.repository;

import com.commerce.config.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
     VerificationCode findByEmail(String email);
     VerificationCode findByOtp(String otp);


}
