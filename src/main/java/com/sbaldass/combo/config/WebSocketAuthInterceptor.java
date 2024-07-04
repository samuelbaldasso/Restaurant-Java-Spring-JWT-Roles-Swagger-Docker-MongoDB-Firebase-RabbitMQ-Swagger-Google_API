package com.sbaldass.combo.config;

import com.sbaldass.combo.services.JwtService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

public class WebSocketAuthInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor!= null) {
            try {
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    handleConnectMessage(accessor);
                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    handleDisconnectMessage(accessor);
                }
            } catch (SecurityException e) {
                System.out.println("Security exception: " + e.getMessage());
            }
        }
        return message;
    }

    private void handleConnectMessage(StompHeaderAccessor accessor) {
        // Extract token from header
        String token = accessor.getFirstNativeHeader("Authorization");

        // Validate token (example logic)
        if (isValidToken(token)) {
            // Set user authentication based on token
            assert token != null;
            accessor.setUser(new UserPrincipal(token)); // Example: UserPrincipal implements Principal
        } else {
            throw new SecurityException("Invalid token");
        }
    }

    private void handleDisconnectMessage(StompHeaderAccessor accessor) {
        // Remove user authentication
        accessor.setUser(null);
        System.out.println("Disconnect message received");
    }

    private boolean isValidToken(String token) {
        // Validate token against a database or a token validation service
        // For example:
        return jwtService.validateToken(token);
    }
}