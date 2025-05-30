package com.commerce.config.entity;

import com.commerce.config.entity.User;
import com.commerce.model.entity.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String otp;
    private String email;
    @OneToOne
    private User user;
    @OneToOne
    private Seller seller;

}
