package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.SupportTicket;
import com.sbaldass.combo.services.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/support")
public class TicketController {

    @Autowired
    private SupportTicketService supportService;

    @GetMapping("/tickets")
    public ResponseEntity<List<SupportTicket>> getSupportTickets(@RequestParam String customerId) {
        List<SupportTicket> tickets = supportService.getSupportTickets(customerId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/tickets")
    public ResponseEntity<SupportTicket> createSupportTicket(@RequestBody SupportTicket supportTicket) {
        SupportTicket createdTicket = supportService.createSupportTicket(supportTicket);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/tickets/{ticketId}")
    public ResponseEntity<SupportTicket> updateSupportTicket(@PathVariable String ticketId, @RequestBody SupportTicket supportTicketDetails) {
        SupportTicket updatedTicket = supportService.updateSupportTicket(ticketId, supportTicketDetails);
        return ResponseEntity.ok(updatedTicket);
    }

}
