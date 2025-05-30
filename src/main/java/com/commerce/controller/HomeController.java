package com.commerce.controller;


import com.commerce.reponse.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public  ApiResponse HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Découvrez notre écosystème e-commerce multi-fournisseurs.");

        return apiResponse;

    }



}
