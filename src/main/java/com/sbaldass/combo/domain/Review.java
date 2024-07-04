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
@Document(collection = "orders")
public class Review {
    @Id
    private Long id;

    private String customerId;
    private String motoboyId;
    private int rating; // e.g., 1 to 5
    private String comment;
    private LocalDateTime createdDate;

}

