package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionDTO dto);

    List<SubscriptionDTO> getAllSubscriptions();

    SubscriptionDTO upgradePlan(
            Long subscriptionId,
            String newPlan,
            Double newAmount
    );

    SubscriptionDTO pauseSubscription(Long subscriptionId);

    SubscriptionDTO resumeSubscription(Long subscriptionId);

    List<SubscriptionDTO> getExpiredSubscriptions();
}
