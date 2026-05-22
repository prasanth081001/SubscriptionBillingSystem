package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

@Data
public class PlanDTO {
    private String planName;

    private Double price;

    private String billingCycle;

    private Integer maxUsers;

    private Integer maxStorage;

    private String status;
}
