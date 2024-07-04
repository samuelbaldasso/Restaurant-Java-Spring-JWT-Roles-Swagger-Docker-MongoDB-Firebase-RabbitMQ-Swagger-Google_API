package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.SupportTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SupportTicketRepository extends MongoRepository<SupportTicket, String> {
    List<SupportTicket> findByCustomerId(String customerId);
}
