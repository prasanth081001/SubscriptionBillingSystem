package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

@Data
public class PaymentDTO {
    private Double amount;

    private String paymentMethod;

    private String paymentStatus;

    private Long billingId;
}
