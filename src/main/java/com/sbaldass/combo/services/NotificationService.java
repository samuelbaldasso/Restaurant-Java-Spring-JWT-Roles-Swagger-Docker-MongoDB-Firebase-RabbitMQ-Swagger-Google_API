package com.sbaldass.combo.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void notifyUser(String userId, String motoboyId) {
        // Simulate sending a notification to the user about the assigned motoboy
        logger.info("Notifying user {} about assigned motoboy {}", userId, motoboyId);

        // Construct the notification
        Notification notification = Notification.builder()
                .setTitle("Motoboy Assigned")
                .setBody("A motoboy has been assigned to your request. Motoboy ID: " + motoboyId)
                .build();

        // Construct the message
        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(userId) // Assumes userId is used as the topic
                .build();

        // Send the notification
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            logger.info("Successfully sent message: {}", response);
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage());
        }
    }

    public void notifyMotoboy(String motoboyId, String requestId) {
        // Simulate sending a notification to the motoboy about the new request
        logger.info("Notifying motoboy {} about new request {}", motoboyId, requestId);

        // Construct the notification
        Notification notification = Notification.builder()
                .setTitle("New Request Assigned")
                .setBody("You have been assigned a new request. Request ID: " + requestId)
                .build();

        // Construct the message
        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(motoboyId) // Assumes motoboyId is used as the topic
                .build();

        // Send the notification
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            logger.info("Successfully sent message: {}", response);
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage());
        }
    }

    public void notifyUserNoMotoboyAvailable(String userId) {
        // Simulate sending a notification to the user that no motoboy is available
        logger.info("Notifying user {} that no motoboy is available", userId);

        // Construct the notification
        Notification notification = Notification.builder()
                .setTitle("No Motoboy Available")
                .setBody("We are sorry, but no motoboy is available at the moment. Please try again later.")
                .build();

        // Construct the message
        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(userId) // Assumes userId is used as the topic
                .build();

        // Send the notification
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            logger.info("Successfully sent message: {}", response);
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage());
        }
    }
}
