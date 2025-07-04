package com.commerce.config.controller;

import com.commerce.config.entity.User;
import com.commerce.config.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

private  final UserService userService;


    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(
            @RequestHeader ("Authorization")String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(user);


    }






}
