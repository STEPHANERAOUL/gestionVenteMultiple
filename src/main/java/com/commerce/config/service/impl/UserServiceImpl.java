package com.commerce.config.service.impl;

import com.commerce.config.JwtProvider;
import com.commerce.config.entity.User;
import com.commerce.config.repository.UserRepository;
import com.commerce.config.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private  final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
      return   this.findUserByEmail(email);



    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found with - "+email);
        }
        return user;
    }
}
