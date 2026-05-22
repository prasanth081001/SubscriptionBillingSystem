package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

@Data
public class BillingDTO {
    private Double amount;

    private String paymentStatus;

    private Long subscriptionId;
}
