package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.BillingDTO;
import com.example.SubscriptionBillingSystem.Entity.BillingEntity;
import com.example.SubscriptionBillingSystem.Entity.SubscriptionEntity;
import com.example.SubscriptionBillingSystem.Repository.BillingRepository;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionRepository;
import com.example.SubscriptionBillingSystem.Service.BillingService;
import com.example.SubscriptionBillingSystem.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;

    private final SubscriptionRepository subscriptionRepository;


    public BillingDTO generateInvoice(BillingDTO dto) {

        SubscriptionEntity subscription =
                subscriptionRepository.findById(dto.getSubscriptionId())
                        .orElseThrow(() ->
                                new RuntimeException("Subscription not found"));

        double gst =
                dto.getAmount() *
                        AppConstants.GST_PERCENTAGE / 100;

        double finalAmount = dto.getAmount() + gst;

        BillingEntity billing = BillingEntity.builder()
                .invoiceNumber(
                        "INV-" + System.currentTimeMillis())
                .amount(dto.getAmount())
                .gstAmount(gst)
                .finalAmount(finalAmount)
                .billingDate(LocalDate.now())
                .paymentStatus(dto.getPaymentStatus())
                .subscription(subscription)
                .build();

        billingRepository.save(billing);

        BillingDTO response = new BillingDTO();

        response.setAmount(billing.getFinalAmount());
        response.setPaymentStatus(billing.getPaymentStatus());
        response.setSubscriptionId(subscription.getSubscriptionId());

        return response;
    }
}
