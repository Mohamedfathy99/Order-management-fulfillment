package com.fathy.order_management_fulfillment.controller;

import com.fathy.order_management_fulfillment.entity.Order;
import com.fathy.order_management_fulfillment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get-order")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/make-order")
    public ResponseEntity<List<Order>> createOrders(@RequestBody List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Set the order reference in each product
        orders.forEach(order -> order.getProducts().forEach(product -> product.setOrder(order)));

        // Save orders and start fulfillment
        List<Order> createdOrders = orderService.saveAllOrders(orders);
        createdOrders.forEach(order -> orderService.startOrderFulfillment(order));

        return ResponseEntity.ok(createdOrders);
    }
}
