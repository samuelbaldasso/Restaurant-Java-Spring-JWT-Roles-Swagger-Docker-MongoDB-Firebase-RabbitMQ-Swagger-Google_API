package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Document(collection = "motoboy_requests")
public class MotoboyRequest {
    @Id
    private String id;
    private Location pickupLocation;
    private Location dropoffLocation;
    private String userId;
    private String motoboyId;
    private MotoboyStatus status;

}
