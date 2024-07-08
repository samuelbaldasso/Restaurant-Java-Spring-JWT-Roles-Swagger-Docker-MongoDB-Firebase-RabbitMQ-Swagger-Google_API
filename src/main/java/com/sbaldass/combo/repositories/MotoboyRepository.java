package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.*;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MotoboyRepository extends MongoRepository<Motoboy, String> {
    List<Motoboy> findByLocationNearAndStatus(Point location, Distance distance, MotoboyStatus status);
}
