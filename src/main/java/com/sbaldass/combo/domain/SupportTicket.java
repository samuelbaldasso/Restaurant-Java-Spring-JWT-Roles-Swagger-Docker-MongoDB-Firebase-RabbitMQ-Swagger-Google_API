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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Document(collection = "tickets")
public class SupportTicket {
    @Id
    private Long id;

    private String ticketId;
    private String customerId;
    private String subject;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
