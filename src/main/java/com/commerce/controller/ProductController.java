package com.commerce.controller;

import com.commerce.config.service.UserService;
import com.commerce.exceptions.ProductException;
import com.commerce.model.entity.Product;
import com.commerce.service.ProductService;
import com.commerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private  final ProductService productService;
    private final UserService userService;
    //private final CategoryService categoryService;
    private  final SellerService sellerService;

    @GetMapping("/{produitId}" )
    public ResponseEntity<Product>getProductById(@PathVariable Long produitId)throws ProductException {
        Product product = productService.findProductById(produitId);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>>searchProduct(@RequestParam(required = false)String query)throws ProductException{
        List<Product> products= productService.searchProducts(query);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping
 public ResponseEntity<Page<Product>>getAllProducts (
                @RequestParam(required = false) String category,
                @RequestParam(required = false) String brand,
                  @RequestParam(required = false) String color,
                @RequestParam(required = false) String size,
                @RequestParam(required = false) Integer minPrice,
                @RequestParam(required = false) Integer maxPrice,
                @RequestParam(required = false) Integer minDiscount,
                @RequestParam(required = false) String sort,
                @RequestParam(required = false) String stock,
                  @RequestParam(defaultValue = "0") Integer pageNumber
                           ){

        return new ResponseEntity<>(
                productService.getAllProducts(category,brand,color,
                        size,minPrice,maxPrice,minDiscount,sort,stock,pageNumber),HttpStatus.OK);



    }










}
