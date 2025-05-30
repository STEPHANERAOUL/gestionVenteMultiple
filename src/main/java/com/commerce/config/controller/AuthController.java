package com.commerce.config.controller;


import com.commerce.config.entity.VerificationCode;
import com.commerce.config.repository.UserRepository;
import com.commerce.config.request.LoginOtpRequet;
import com.commerce.config.request.LoginRequest;
import com.commerce.config.service.AuthService;
import com.commerce.model.domain.USER_ROLE;
import com.commerce.reponse.ApiResponse;
import com.commerce.reponse.AuthResponse;
import com.commerce.reponse.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
     private  final UserRepository userRepository;
     private final AuthService authService;
        @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest request)  {

            String jwt= authService.createUser(request);
            AuthResponse res = new AuthResponse();
            res.setJwt(jwt);
            res.setMessage("register success");
            res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);


    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequet req) throws Exception {

        authService.sentLoginOtp(req.getEmail(),req.getRole());
        ApiResponse res = new ApiResponse();

        res.setMessage("otp sent successfully");

        return ResponseEntity.ok(res);


    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

       AuthResponse authResponse= authService.signing(req);


        return ResponseEntity.ok(authResponse);


    }



}
