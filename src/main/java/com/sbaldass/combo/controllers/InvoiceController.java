package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.Invoice;
import com.sbaldass.combo.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/orders")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{orderId}/invoice")
    public ResponseEntity<Invoice> getInvoiceByOrderId(@PathVariable String orderId) {
        Invoice invoice = invoiceService.getInvoiceByOrderId(orderId);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/{orderId}/invoice")
    public ResponseEntity<Invoice> generateInvoice(@PathVariable String orderId) {
        Invoice invoice = invoiceService.generateInvoice(orderId);
        return ResponseEntity.ok(invoice);
    }
}
