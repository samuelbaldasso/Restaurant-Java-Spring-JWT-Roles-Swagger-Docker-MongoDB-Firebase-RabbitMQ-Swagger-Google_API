package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.OrderRequestDTO;
import com.sbaldass.combo.dto.OrderResponseDTO;
import com.sbaldass.combo.dto.OrderStatusUpdateDTO;
import com.sbaldass.combo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDto) {
        OrderResponseDTO orderResponseDto = orderService.placeOrder(orderRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PreAuthorize("hasRole('MOTOBOY')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String orderId) {
        OrderResponseDTO orderResponseDto = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PreAuthorize("hasRole('MOTOBOY')")
    @GetMapping("/active")
    public ResponseEntity<List<OrderResponseDTO>> getActiveOrders(@RequestParam String motoboyId) {
        List<OrderResponseDTO> activeOrders = orderService.getActiveOrders(motoboyId);
        return ResponseEntity.ok(activeOrders);
    }

    @PreAuthorize("hasRole('MOTOBOY')")
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable String orderId, @RequestBody OrderStatusUpdateDTO orderStatusUpdateDto, @RequestParam String motoboyId) {
        OrderResponseDTO orderResponseDto = orderService.updateOrderStatus(orderId, orderStatusUpdateDto, motoboyId);
        return ResponseEntity.ok(orderResponseDto);
    }
}
