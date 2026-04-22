package com.bookkeeper.app.service;

import com.bookkeeper.app.entity.Order;
import com.bookkeeper.app.entity.OrderItem;
import com.bookkeeper.app.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByCustomerEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByDateRange(startDate, endDate);
    }

    public List<Order> searchByCustomerName(String name) {
        return orderRepository.findByCustomerNameContaining(name);
    }

    public Order createOrder(Order order) {
        calculateOrderTotal(order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);

        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerEmail(orderDetails.getCustomerEmail());
        order.setCustomerAddress(orderDetails.getCustomerAddress());
        order.setCustomerPhone(orderDetails.getCustomerPhone());
        order.setStatus(orderDetails.getStatus());
        order.setNotes(orderDetails.getNotes());

        if (orderDetails.getOrderItems() != null && !orderDetails.getOrderItems().isEmpty()) {
            order.setOrderItems(orderDetails.getOrderItems());
            calculateOrderTotal(order);
        }

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    private void calculateOrderTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            item.setSubtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            total = total.add(item.getSubtotal());
        }
        order.setTotalAmount(total);
    }
}
