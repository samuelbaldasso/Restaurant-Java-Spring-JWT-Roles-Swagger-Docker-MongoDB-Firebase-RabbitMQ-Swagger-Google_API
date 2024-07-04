package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.TrackingUpdate;
import com.sbaldass.combo.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @MessageMapping("customer/track/{orderId}")
    @SendTo("/topic/updates/{orderId}")
    public void sendTrackingUpdate(@PathVariable String orderId, TrackingUpdate update) {
        trackingService.sendUpdate(orderId, update);
    }

}
