package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String customerId;

    private String motoboyId;

    private OrderStatus orderStatus;

    private LocalDateTime eta;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String pickupLocation;

    private String deliveryLocation;

    private LocalDate orderTime;

    private String details;

    private double totalAmount;

}
