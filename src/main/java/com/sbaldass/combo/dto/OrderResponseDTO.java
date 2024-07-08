package com.sbaldass.combo.dto;

import com.sbaldass.combo.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponseDTO {
    private String id;
    private String customerId;
    private String pickupLocation;
    private String deliveryLocation;
    private OrderStatus status;
    private String motoboyId;
    private LocalDate eta;

}
