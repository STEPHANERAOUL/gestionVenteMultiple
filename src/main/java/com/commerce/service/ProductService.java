package com.commerce.service;

import com.commerce.config.request.CreateProductRequest;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Seller;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req, Seller seller);
    public void  deleteProduct(Long productId);
    public  Product updateProduct(Long productId, Product product);
    Product findProductById(Long productId);
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(String category,String brand,
                                        String colors,String size,
                                        Integer minPrice,
                                        Integer maxPrice,
                                        Integer minDiscount,
                                        String sort,
                                        String stock,
                                        Integer pageNumber);


List<Product> getProductBySellerId(Long sellerId);


}
