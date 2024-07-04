package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.SupportTicket;
import com.sbaldass.combo.repositories.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.sbaldass.combo.domain.TicketStatus.OPEN;

@Service
public class SupportTicketService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public List<SupportTicket> getSupportTickets(String customerId) {
        return supportTicketRepository.findByCustomerId(customerId);
    }

    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        supportTicket.setCreatedDate(LocalDateTime.now());
        supportTicket.setUpdatedDate(LocalDateTime.now());
        supportTicket.setStatus(OPEN);
        return supportTicketRepository.save(supportTicket);
    }

    public SupportTicket updateSupportTicket(String ticketId, SupportTicket supportTicketDetails) {
        SupportTicket supportTicket = supportTicketRepository.findById(ticketId).orElseThrow();
        supportTicket.setSubject(supportTicketDetails.getSubject());
        supportTicket.setDescription(supportTicketDetails.getDescription());
        supportTicket.setStatus(supportTicketDetails.getStatus());
        supportTicket.setUpdatedDate(LocalDateTime.now());
        return supportTicketRepository.save(supportTicket);
    }
}

