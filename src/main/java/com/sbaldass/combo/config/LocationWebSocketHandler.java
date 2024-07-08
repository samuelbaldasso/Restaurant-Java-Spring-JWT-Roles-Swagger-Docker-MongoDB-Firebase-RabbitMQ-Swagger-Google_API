package com.sbaldass.combo.config;

import com.sbaldass.combo.services.GoogleGeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class LocationWebSocketHandler implements WebSocketHandler {

    private final GoogleGeolocationService geolocationService;

    @Autowired
    public LocationWebSocketHandler(GoogleGeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText) // Assume the message contains the necessary data for geolocation
                .flatMap(geolocationService::getAccurateLocation)
                .map(location -> "Received accurate location update: " + location)
                .map(session::textMessage)
                .as(session::send);
    }
}