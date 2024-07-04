package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByMotoboyId(String motoboyId);
}
