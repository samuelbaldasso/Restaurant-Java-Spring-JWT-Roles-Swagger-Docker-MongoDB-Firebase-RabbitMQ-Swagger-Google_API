package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.Review;
import com.sbaldass.combo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{motoboyId}")
    public ResponseEntity<List<Review>> getReviewsByMotoboyId(@PathVariable String motoboyId) {
        List<Review> reviews = reviewService.getReviewsByMotoboyId(motoboyId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }
}
