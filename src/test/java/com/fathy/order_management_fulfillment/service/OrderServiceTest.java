package com.fathy.order_management_fulfillment.service;

import com.fathy.order_management_fulfillment.entity.Order;
import com.fathy.order_management_fulfillment.entity.Product;
import com.fathy.order_management_fulfillment.repository.OrderRepository;
import com.fathy.order_management_fulfillment.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateOrder_ThrowsExceptionWhenNoProducts() {
        Order emptyOrder = new Order();
        emptyOrder.setProducts(new HashSet<>()); // Set an empty product set
        assertThrows(IllegalArgumentException.class,
                () -> orderService.validateOrder(emptyOrder), "Order must contain at least one product.");
    }

    @Test
    void testValidateOrder_ThrowsExceptionWhenProductPriceIsZeroOrNegative() {
        Order order = new Order();
        Set<Product> products = new HashSet<>();
        Product product = new Product();
        product.setPrice(-10.0); // Set an invalid price
        product.setQuantity(1); // Valid quantity
        products.add(product);
        order.setProducts(products);

        assertThrows(IllegalArgumentException.class, () -> orderService.validateOrder(order), "Product price must be greater than 0.");
    }

    @Test
    void testValidateOrder_ThrowsExceptionWhenProductQuantityIsNotOne() {
        Order order = new Order();
        Set<Product> products = new HashSet<>();
        Product product = new Product();
        product.setPrice(10.0); // Valid price
        product.setQuantity(2); // Invalid quantity
        products.add(product);
        order.setProducts(products);

        assertThrows(IllegalArgumentException.class, () -> orderService.validateOrder(order), "Product quantity must be exactly one.");
    }
}
