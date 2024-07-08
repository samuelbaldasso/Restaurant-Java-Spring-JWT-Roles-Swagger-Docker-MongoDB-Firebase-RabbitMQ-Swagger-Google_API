package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.TrackingMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TrackingController {

    @MessageMapping("/track")
    @SendTo("/topic/track")
    public TrackingMessageDto sendTrackingUpdate(TrackingMessageDto trackingMessage) {
        return trackingMessage;
    }
}