package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Order;
import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.RoleName;
import com.sbaldass.combo.dto.InvoiceDto;
import com.sbaldass.combo.dto.OrderHistoryDto;
import com.sbaldass.combo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderHistoryDto> getOrderHistory(String userId, String role) {
        List<Order> orders;
        if ("customer".equals(role)) {
            orders = orderRepository.findByCustomerId(userId);
        } else if ("motoboy".equals(role)) {
            orders = orderRepository.findByMotoboyId(userId);
        } else {
            throw new IllegalArgumentException("Invalid user role");
        }

        return orders.stream().map(this::mapToOrderHistoryDto).collect(Collectors.toList());
    }

    public InvoiceDto getInvoice(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToInvoiceDto(order);
    }

    private OrderHistoryDto mapToOrderHistoryDto(Order order) {
        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
        orderHistoryDto.setId(order.getId());
        orderHistoryDto.setCustomerId(order.getCustomerId());
        orderHistoryDto.setMotoboyId(order.getMotoboyId());
        orderHistoryDto.setPickupLocation(order.getPickupLocation());
        orderHistoryDto.setDeliveryLocation(order.getDeliveryLocation());
        orderHistoryDto.setStatus(String.valueOf(order.getOrderStatus()));
        orderHistoryDto.setEta(order.getEta());
        orderHistoryDto.setCompletedAt(order.getEta().plusHours(2)); // Dummy implementation

        return orderHistoryDto;
    }

    private InvoiceDto mapToInvoiceDto(Order order) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setOrderId(order.getId());
        invoiceDto.setCustomerId(order.getCustomerId());
        invoiceDto.setMotoboyId(order.getMotoboyId());
        invoiceDto.setPickupLocation(order.getPickupLocation());
        invoiceDto.setDeliveryLocation(order.getDeliveryLocation());
        invoiceDto.setStatus(String.valueOf(order.getOrderStatus()));
        invoiceDto.setAmount(100.0); // Dummy implementation
        invoiceDto.setCompletedAt(order.getEta().plusHours(2)); // Dummy implementation

        return invoiceDto;
    }
}