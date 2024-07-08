package com.sbaldass.combo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoryDto {
    private String id;
    private String customerId;
    private String motoboyId;
    private String pickupLocation;
    private String deliveryLocation;
    private String status;
    private LocalDateTime eta;
    private LocalDateTime completedAt;

}