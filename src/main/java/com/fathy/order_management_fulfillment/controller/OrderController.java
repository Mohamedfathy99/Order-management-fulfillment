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


    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order == null) {
            return ResponseEntity.badRequest().build();
        }
        Order createdOrder = orderService.saveOrder(order);
        orderService.startOrderFulfillment(createdOrder);
        return ResponseEntity.ok(createdOrder);
    }
}
