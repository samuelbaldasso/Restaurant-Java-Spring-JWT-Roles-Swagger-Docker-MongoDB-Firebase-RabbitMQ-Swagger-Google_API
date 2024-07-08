package com.sbaldass.combo.dto;

import com.sbaldass.combo.domain.OrderStatus;
import lombok.Data;

@Data
public class TrackingMessageDto {
    private String orderId;
    private double latitude;
    private double longitude;
    private OrderStatus status;
}
