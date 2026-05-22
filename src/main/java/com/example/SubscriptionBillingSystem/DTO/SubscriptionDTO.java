package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionDTO {
    private String planName;

    private Double amount;

    private String billingCycle;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean autoRenew;

    private String status;

    private Long customerId;
}
