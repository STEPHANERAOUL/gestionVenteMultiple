package com.commerce.config.service.impl;

import com.commerce.config.entity.User;
import com.commerce.config.request.CreateReviewRequest;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Review;
import com.commerce.repository.ReviewRepository;
import com.commerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;



    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review =  new Review();
        review.setProduct(product);
        review.setReviewText(review.getReviewText());
        review.setRating(review.getRating());
        review.setProductImages(review.getProductImages());

        product.getReviews().add(review);


        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {

        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (review.getUser().getId().equals(userId)){
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewRepository.save(review);
        }

       throw new Exception("you can't update this review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review =getReviewById(reviewId);
        if (review.getUser().getId().equals(userId)){
            throw  new Exception("you can't delete this review");
        }
        reviewRepository.delete(review);

    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(()-> new Exception("review not found"));
    }
}
