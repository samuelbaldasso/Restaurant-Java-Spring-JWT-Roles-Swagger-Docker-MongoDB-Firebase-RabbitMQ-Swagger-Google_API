package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.dto.InvoiceDto;
import com.sbaldass.combo.dto.OrderHistoryDto;
import com.sbaldass.combo.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/history")
    public ResponseEntity<List<OrderHistoryDto>> getOrderHistory(Authentication authentication) {
        String userId = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        List<OrderHistoryDto> orderHistory = orderHistoryService.getOrderHistory(userId, role);
        return ResponseEntity.ok(orderHistory);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{orderId}/invoice")
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable String orderId) {
        InvoiceDto invoice = orderHistoryService.getInvoice(orderId);
        return ResponseEntity.ok(invoice);
    }
}
