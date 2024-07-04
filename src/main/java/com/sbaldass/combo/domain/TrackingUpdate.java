package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Document(collection = "tracking_update")
public class TrackingUpdate {
    private String orderId;
    private String status;
    private double latitude;
    private double longitude;
}
