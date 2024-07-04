package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.TrackingHistory;
import com.sbaldass.combo.domain.TrackingUpdate;
import com.sbaldass.combo.repositories.TrackingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TrackingHistoryRepository trackingHistoryRepository;

    public void sendUpdate(String orderId, TrackingUpdate update) {
        messagingTemplate.convertAndSend("/topic/updates/" + orderId, update);
        saveTrackingHistory(orderId, update);
    }

    private void saveTrackingHistory(String orderId, TrackingUpdate update) {
        TrackingHistory history = new TrackingHistory();
        history.setOrderId(orderId);
        history.setLatitude(update.getLatitude());
        history.setLongitude(update.getLongitude());
        history.setTimestamp(LocalDateTime.now());
        trackingHistoryRepository.save(history);
    }

    public List<TrackingHistory> getTrackingHistory(String orderId) {
        return trackingHistoryRepository.findByOrderId(orderId);
    }
}
