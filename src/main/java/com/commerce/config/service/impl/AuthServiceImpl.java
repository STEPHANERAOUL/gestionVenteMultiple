package com.commerce.config.service.impl;

import com.commerce.config.JwtProvider;
import com.commerce.config.entity.User;
import com.commerce.config.entity.VerificationCode;
import com.commerce.config.repository.UserRepository;
import com.commerce.config.repository.VerificationCodeRepository;
import com.commerce.config.request.LoginRequest;
import com.commerce.config.service.AuthService;
import com.commerce.config.service.EmailService;
import com.commerce.config.utils.OtpUtil;
import com.commerce.model.domain.USER_ROLE;
import com.commerce.model.entity.Cart;
import com.commerce.model.entity.Seller;
import com.commerce.reponse.AuthResponse;
import com.commerce.reponse.SignupRequest;
import com.commerce.repository.CartRepository;
import com.commerce.repository.SellerRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
private final UserRepository userRepository;
private  final PasswordEncoder passwordEncoder;
private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
   private final EmailService emailService;
   private final  CustomUserServiceImpl customUserService;
   private final SellerRepository sellerRepository;

   @Override
    public void sentLoginOtp(String email,USER_ROLE role) throws Exception {
        String SIGNING_PREFIX ="signing_";

        if(email.startsWith(SIGNING_PREFIX)){
            email = email.substring(SIGNING_PREFIX.length());
            if (role.equals(USER_ROLE.ROLE_SELLER)){
                Seller seller = sellerRepository.findByEmail(email);
                if (seller==null){
                    throw new Exception("seller not found");
                }

            }else {
                System.out.println("email"+email);
                User user = userRepository.findByEmail(email);
                if (user==null) {
                   // throw new Exception("user not exist with provided email");
                }




            }
        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist!=null){
            verificationCodeRepository.delete(isExist);

        }

        String otp= OtpUtil.generatedOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "zosh bazaar login/signup otp";
        String text = "your login/signup otp is -"+otp;
        emailService.sendVerificationOtpEmail(email,otp,subject,text);


      // return SIGNING_PREFIX;
   }

    @Override
    public String createUser(SignupRequest request) {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(request.getEmail());
        if (verificationCode==null || !verificationCode.getOtp().equals(request.getOtp())){
            try {
               throw  new Exception("wrong otp..");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        User user = userRepository.findByEmail(request.getEmail());

        if(user  == null){
            User createdUser = new User();
            createdUser.setEmail(request.getEmail());
            createdUser.setFullname(request.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("679546868");
            createdUser.setPassword(passwordEncoder.encode(request.getOtp()));

            user = userRepository.save(createdUser);
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);


        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signing(LoginRequest req) throws Exception {
       String username = req.getEmail();
       String otp = req.getOtp();
       Authentication authentication = authenticate(username,otp);
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtProvider.generateToken(authentication);

       AuthResponse authResponse = new AuthResponse();
       authResponse.setJwt(token);
       authResponse.setMessage("Login success");
        Collection<? extends  GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(roleName));


        return authResponse;
    }

    private Authentication authenticate(String username, String otp) throws Exception {
      UserDetails userDetails= customUserService.loadUserByUsername(username);
        String SELLER_PREFIX="seller_";
        if(username.startsWith(SELLER_PREFIX)){
            username= username.substring(SELLER_PREFIX.length());

        }

      if (userDetails==null){
          throw new BadCredentialsException("invalid username");
      }
      VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
      if (verificationCode == null || verificationCode.getOtp().equals(otp)){
          //throw new BadCredentialsException("wrong otp");
      }

       return  new UsernamePasswordAuthenticationToken(
               userDetails ,
               null,
               userDetails.getAuthorities());

    }
}
