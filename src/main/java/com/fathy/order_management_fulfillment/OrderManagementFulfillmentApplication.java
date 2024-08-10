package com.fathy.order_management_fulfillment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderManagementFulfillmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementFulfillmentApplication.class, args);
	}

}
