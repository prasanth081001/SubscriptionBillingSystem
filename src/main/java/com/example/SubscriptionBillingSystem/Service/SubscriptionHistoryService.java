package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionHistoryDTO;

import java.util.List;

public interface SubscriptionHistoryService {
    List<SubscriptionHistoryDTO> getHistoryBySubscription(Long subscriptionId);
}
