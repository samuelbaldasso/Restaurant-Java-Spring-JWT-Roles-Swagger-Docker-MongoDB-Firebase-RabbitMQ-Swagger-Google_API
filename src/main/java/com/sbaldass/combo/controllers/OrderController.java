package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.Order;
import com.sbaldass.combo.domain.OrderStatus;
import com.sbaldass.combo.domain.TrackingHistory;
import com.sbaldass.combo.domain.TrackingUpdate;
import com.sbaldass.combo.services.OrderService;
import com.sbaldass.combo.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TrackingService trackingService;

    @PostMapping("/{orderId}/location")
    public ResponseEntity<Void> updateLocation(@PathVariable String orderId, @RequestBody TrackingUpdate update) {
        trackingService.sendUpdate(orderId, update);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam String customerId) {
        List<Order> orders = orderService.getOrderHistory(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}/tracking-history")
    public ResponseEntity<List<TrackingHistory>> getTrackingHistory(@PathVariable String orderId) {
        List<TrackingHistory> history = trackingService.getTrackingHistory(orderId);
        return ResponseEntity.ok(history);
    }


    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.status(201).body(placedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }
}
