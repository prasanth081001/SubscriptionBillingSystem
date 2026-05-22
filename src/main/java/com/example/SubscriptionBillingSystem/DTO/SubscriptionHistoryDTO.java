package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionHistoryDTO {
    private String actionType;

    private String oldValue;

    private String newValue;

    private String remarks;

    private LocalDateTime actionTime;
}
