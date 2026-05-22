package com.example.SubscriptionBillingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscriptionBillingSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(SubscriptionBillingSystemApplication.class, args);
	}

}
