package com.commerce.controller;


import com.commerce.config.entity.User;
import com.commerce.config.request.CreateReviewRequest;
import com.commerce.config.service.UserService;
import com.commerce.model.entity.Product;
import com.commerce.model.entity.Review;
import com.commerce.reponse.ApiResponse;
import com.commerce.service.ProductService;
import com.commerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;



    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewsByProductId(
            @PathVariable Long productId

    ){
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);

    }
@PostMapping("/produts/{productId}/reviews")
    public ResponseEntity<Review> writeReview(
        @RequestBody CreateReviewRequest req,
        @PathVariable Long productId,
        @RequestHeader("Authorization")String jwt

        ) throws Exception {

    User user = userService.findUserByJwtToken(jwt);
    Product product = productService.findProductById(productId);
    Review review = reviewService.createReview(
            req,
            user,
            product

    );
    return ResponseEntity.ok(review);

}

@PatchMapping("/reviews/{reviewId}")
public ResponseEntity<Review> updateReview(
        @RequestBody CreateReviewRequest req,
        @PathVariable Long reviewId,
        @RequestHeader("Authorization") String jwt

) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Review review = reviewService.updateReview(reviewId,req.getReviewText(),req.getReviewRating(),user.getId());

    return ResponseEntity.ok(review);
}


@DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt

) throws Exception {
    User user =userService.findUserByJwtToken(jwt);
    reviewService.deleteReview(reviewId,user.getId());
    ApiResponse res = new ApiResponse();
    res.setMessage("Review deleted successfully");
    //res.setStatus(true);
    return ResponseEntity.ok(res);


}




}
