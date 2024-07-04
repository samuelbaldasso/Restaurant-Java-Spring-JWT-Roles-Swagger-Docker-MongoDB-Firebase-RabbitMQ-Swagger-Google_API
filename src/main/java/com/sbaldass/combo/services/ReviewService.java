package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Review;
import com.sbaldass.combo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByMotoboyId(String motoboyId) {
        return reviewRepository.findByMotoboyId(motoboyId);
    }

    public Review createReview(Review review) {
        review.setCreatedDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }
}
