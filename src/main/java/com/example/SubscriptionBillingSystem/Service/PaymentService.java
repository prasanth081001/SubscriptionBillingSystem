package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.PaymentDTO;

public interface PaymentService {
    PaymentDTO makePayment(PaymentDTO dto);
}
