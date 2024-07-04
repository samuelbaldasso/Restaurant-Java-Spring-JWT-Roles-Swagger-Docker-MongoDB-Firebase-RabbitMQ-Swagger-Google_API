package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;

    private String orderId;
    private LocalDateTime invoiceDate;
    private double totalAmount;
    private String customerId;

}
