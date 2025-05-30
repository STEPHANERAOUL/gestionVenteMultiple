package com.commerce.service;

import com.commerce.config.entity.User;
import com.commerce.config.request.CreateReviewRequest;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review>getReviewByProductId(Long productId);
    Review updateReview(Long reviewId,String reviewText, double rating, Long userId) throws Exception;

    void  deleteReview(Long reviewId,Long userId) throws Exception;
   Review getReviewById(Long reviewId) throws Exception;


}
