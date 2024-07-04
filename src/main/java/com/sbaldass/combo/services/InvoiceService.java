package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Invoice;
import com.sbaldass.combo.domain.Order;
import com.sbaldass.combo.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderService orderService;

    public Invoice generateInvoice(String orderId) {
        Order order = orderService.getOrderById(orderId).orElseThrow();
        Invoice invoice = new Invoice();
        invoice.setId("INV-" + orderId);
        invoice.setOrderId(order.getId());
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setTotalAmount(order.getTotalAmount());
        invoice.setCustomerId(order.getUserId());

        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceByOrderId(String orderId) {
        return invoiceRepository.findByOrderId(orderId);
    }
}
