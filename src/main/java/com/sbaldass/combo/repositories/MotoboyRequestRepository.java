package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.MotoboyRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotoboyRequestRepository extends MongoRepository<MotoboyRequest, String> {
}
