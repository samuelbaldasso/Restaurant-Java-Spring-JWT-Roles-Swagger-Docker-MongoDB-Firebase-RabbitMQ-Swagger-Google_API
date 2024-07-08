package com.sbaldass.combo.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private String pickupLocation;
    private String deliveryLocation;
    private String motoboyId;
    private String customerId;
}
