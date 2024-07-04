package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Order;
import com.sbaldass.combo.domain.OrderStatus;
import com.sbaldass.combo.repositories.OrderRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Order order) {
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderTime(LocalDate.now());
        order.setDetails(order.getDetails());
        order.setDeliveryLocation(order.getDeliveryLocation());
        order.setPickupLocation(order.getPickupLocation());
        order.setCreatedAt(LocalDate.now());
        order.setTotalAmount(order.getTotalAmount());

        order.setUserId(order.getUserId());

        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        order.setDetails(order.getDetails());
        order.setDeliveryLocation(order.getDeliveryLocation());
        order.setPickupLocation(order.getPickupLocation());
        order.setTotalAmount(order.getTotalAmount());

        order.setUpdatedAt(LocalDate.now());
        order.setUserId(order.getUserId());

        return orderRepository.save(order);
    }


    public List<Order> getOrderHistory(String customerId) {
        return orderRepository.findByUserId(customerId);
    }

}
