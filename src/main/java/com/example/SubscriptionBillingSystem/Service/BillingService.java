package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.BillingDTO;

public interface BillingService {
    BillingDTO generateInvoice(BillingDTO dto);
}
