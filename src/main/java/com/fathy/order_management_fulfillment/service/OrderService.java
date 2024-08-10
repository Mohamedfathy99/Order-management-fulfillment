package com.fathy.order_management_fulfillment.service;

import com.fathy.order_management_fulfillment.entity.Order;
import com.fathy.order_management_fulfillment.entity.Product;
import com.fathy.order_management_fulfillment.repository.OrderRepository;
import com.fathy.order_management_fulfillment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    void validateOrder(Order order) {
        Set<Product> products = order.getProducts();

        // Check if the order contains no products
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }

        // Validate each product in the order
        for (Product product : products) {
            if (product.getPrice() <= 0) {
                throw new IllegalArgumentException("Product price must be greater than 0. Negative or zero prices are not allowed.");
            }
            if (product.getQuantity() != 1) {
                throw new IllegalArgumentException("Product quantity must be exactly one.");
            }
        }
    }

    public List<Order> saveAllOrders(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }


    public Order saveOrder(Order order) {
        order.getProducts().forEach(product -> product.setOrder(order));
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
