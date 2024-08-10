package com.fathy.order_management_fulfillment.repository;

import com.fathy.order_management_fulfillment.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveAndFindOrder() {
        Order order = new Order();
        Order savedOrder = orderRepository.save(order);

        assertThat(orderRepository.findById(savedOrder.getId())).isPresent();
    }
}
