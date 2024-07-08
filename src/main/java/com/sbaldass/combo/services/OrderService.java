package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Order;
import com.sbaldass.combo.dto.OrderRequestDTO;
import com.sbaldass.combo.dto.OrderResponseDTO;
import com.sbaldass.combo.dto.OrderStatusUpdateDTO;
import com.sbaldass.combo.dto.TrackingMessageDto;
import com.sbaldass.combo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.sbaldass.combo.domain.OrderStatus.PENDING;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDto) {
        Order order = new Order();
        order.setCustomerId(orderRequestDto.getCustomerId());
        order.setPickupLocation(orderRequestDto.getPickupLocation());
        order.setDeliveryLocation(orderRequestDto.getDeliveryLocation());
        order.setOrderStatus(PENDING);
        order.setMotoboyId(orderRequestDto.getMotoboyId());
        order.setEta(calculateETA(orderRequestDto.getPickupLocation(), orderRequestDto.getDeliveryLocation()));

        orderRepository.save(order);

        // Notify motoboys about the new order
        notifyMotoboys(order);

        return mapToOrderResponseDto(order);
    }

    private LocalDateTime calculateETA(String pickupLocation, String deliveryLocation) {
        // Dummy implementation for ETA calculation based on distance
        double distance = calculateDistance(pickupLocation, deliveryLocation); // Implement this method based on your logic
        int averageSpeed = 40; // average speed in km/h
        double hours = distance / averageSpeed;

        return LocalDateTime.now().plusMinutes((long) (hours * 60));
    }

    private double calculateDistance(String pickupLocation, String deliveryLocation) {
        // Dummy implementation for calculating distance
        // In a real application, you might use a routing API to calculate the actual distance
        return 10.0; // Example distance in km
    }

    public OrderResponseDTO getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToOrderResponseDto(order);
    }

    public List<OrderResponseDTO> getActiveOrders(String motoboyId) {
        List<Order> orders = orderRepository.findByMotoboyIdAndOrderStatus(motoboyId, "IN_PROGRESS");
        return orders.stream().map(this::mapToOrderResponseDto).collect(Collectors.toList());
    }

    public OrderResponseDTO updateOrderStatus(String orderId, OrderStatusUpdateDTO orderStatusUpdateDto, String motoboyId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if (!order.getMotoboyId().equals(motoboyId)) {
            throw new RuntimeException("Unauthorized");
        }
        order.setOrderStatus(orderStatusUpdateDto.getStatus());
        orderRepository.save(order);

        // Send tracking update via WebSocket
        sendTrackingUpdate(order);

        return mapToOrderResponseDto(order);
    }

    private void notifyMotoboys(Order order) {
        sendTrackingUpdate(order);

    }

    private OrderResponseDTO mapToOrderResponseDto(Order order) {
        OrderResponseDTO orderResponseDto = new OrderResponseDTO();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setCustomerId(order.getCustomerId());
        orderResponseDto.setPickupLocation(order.getPickupLocation());
        orderResponseDto.setDeliveryLocation(order.getDeliveryLocation());
        orderResponseDto.setStatus(order.getOrderStatus());
        orderResponseDto.setEta(LocalDate.from(order.getEta()));
        orderResponseDto.setMotoboyId(order.getMotoboyId()); // Include motoboyId

        return orderResponseDto;
    }

    private void sendTrackingUpdate(Order order) {
        TrackingMessageDto trackingMessage = new TrackingMessageDto();
        trackingMessage.setOrderId(order.getId());
        trackingMessage.setStatus(order.getOrderStatus());

        messagingTemplate.convertAndSend("/topic/track", trackingMessage);
    }
}
