package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.TrackingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackingHistoryRepository extends MongoRepository<TrackingHistory, String> {
    List<TrackingHistory> findByOrderId(String orderId);

}
