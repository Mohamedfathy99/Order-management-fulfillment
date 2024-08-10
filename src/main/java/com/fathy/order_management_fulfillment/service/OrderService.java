package com.fathy.order_management_fulfillment.service;

import com.fathy.order_management_fulfillment.entity.Order;
import com.fathy.order_management_fulfillment.entity.Product;
import com.fathy.order_management_fulfillment.repository.OrderRepository;
import com.fathy.order_management_fulfillment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    private void validateOrder(Order order) {
        Set<Product> products = order.getProducts();

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }

        for (Product product : products) {
            if (product.getPrice() <= 0) {
                throw new IllegalArgumentException("Product price must be greater than 0. not negative price!!!");
            }
            if (product.getQuantity() <= 0) {
                throw new IllegalArgumentException("You can't get order without products," +
                        " Product quantity must be one or more.");
            }
        }
    }

    @Transactional
    public Order saveOrder(Order order){
        validateOrder(order);
        return orderRepository.save(order);
    }

    @Async
    public void startOrderFulfillment(Order order){
        //  Order Fulfillment operation
        try {
            Thread.sleep(5000); // Simulate a delay in processing
            System.out.println("Order fulfillment started for Order ID: " + order.getId());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Order fulfillment was interrupted for Order ID: " + order.getId());
        }
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
