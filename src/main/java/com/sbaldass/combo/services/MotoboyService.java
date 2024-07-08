package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Location;
import com.sbaldass.combo.domain.Motoboy;
import com.sbaldass.combo.domain.MotoboyRequest;
import com.sbaldass.combo.domain.MotoboyStatus;
import com.sbaldass.combo.repositories.MotoboyRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoboyService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MotoboyRepository motoboyRepository;

    public List<Motoboy> findNearbyMotoboys(double latitude, double longitude, int radius) {
        Point location = new Point(longitude, latitude);
        Distance distance = new Distance(radius / 1000.0, Metrics.KILOMETERS);

        return motoboyRepository.findByLocationNearAndStatus(location, distance, MotoboyStatus.AVAILABLE);
    }

    public void processMotoboyRequest(MotoboyRequest request) {
        // Send request to the queue
        rabbitTemplate.convertAndSend("motoboyRequestsQueue", request);
    }

    public void acceptRequest(String requestId, String name) {
        // Send request to the queue
        rabbitTemplate.convertAndSend("motoboyAcceptQueue", requestId);
    }

    public void rejectRequest(String requestId, String name) {
        // Send request to the queue
        rabbitTemplate.convertAndSend("motoboyRejectQueue", requestId);
    }

    public void updateLocation(String motoboyId, String newLocation) {
        Motoboy motoboy = motoboyRepository.findById(motoboyId).
                orElseThrow(() -> new IllegalArgumentException("Invalid motoboy ID"));

        String[] location = newLocation
                .split(",");
        motoboy.setLocation(new Location(Double.parseDouble(location[1]), Double.parseDouble(location[0])));
        motoboyRepository.save(motoboy);
    }
}
