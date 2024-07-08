package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByMotoboyIdAndOrderStatus(String motoboyId, String status);
    List<Order> findByCustomerId(String customerId);
    List<Order> findByMotoboyId(String motoboyId);
}
