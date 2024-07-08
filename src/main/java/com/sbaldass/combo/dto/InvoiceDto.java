package com.sbaldass.combo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {
    private String orderId;
    private String customerId;
    private String motoboyId;
    private String pickupLocation;
    private String deliveryLocation;
    private String status;
    private double amount;
    private LocalDateTime completedAt;

}
