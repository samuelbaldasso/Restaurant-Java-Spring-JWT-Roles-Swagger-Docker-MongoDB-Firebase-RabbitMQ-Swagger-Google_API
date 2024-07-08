package com.sbaldass.combo.dto;

import com.sbaldass.combo.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusUpdateDTO {
    private OrderStatus status;
}
