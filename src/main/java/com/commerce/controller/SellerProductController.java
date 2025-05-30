package com.commerce.controller;

import com.commerce.config.request.CreateProductRequest;
import com.commerce.config.service.UserService;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Seller;
import com.commerce.service.ProductService;
import com.commerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {
    private final ProductService productService;
    private final SellerService sellerService;
    private final UserService userService;
   // private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProductBySellerId(
           @RequestHeader("Authorization") String jwt ) throws Exception {
       Seller seller = sellerService.getSellerProfile(jwt);
        List<Product> products = productService.getProductBySellerId(seller.getId());
                return new ResponseEntity<>(products, HttpStatus.OK);

    }
    @PostMapping()
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwt ) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        Product product = productService.createProduct(request,seller);
        return new ResponseEntity<>(product,HttpStatus.CREATED);

    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product){

            Product updateProduct = productService.updateProduct(productId,product);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);


        }

    }




