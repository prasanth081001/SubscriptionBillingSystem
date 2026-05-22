package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.PaymentDTO;
import com.example.SubscriptionBillingSystem.Entity.BillingEntity;
import com.example.SubscriptionBillingSystem.Entity.PaymentEntity;
import com.example.SubscriptionBillingSystem.Repository.BillingRepository;
import com.example.SubscriptionBillingSystem.Repository.PaymentRepository;
import com.example.SubscriptionBillingSystem.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final BillingRepository billingRepository;

    @Override
    public PaymentDTO makePayment(PaymentDTO dto) {

        BillingEntity billing =
                billingRepository.findById(dto.getBillingId())
                        .orElseThrow(() ->
                                new RuntimeException("Billing not found"));

        PaymentEntity payment = PaymentEntity.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus(dto.getPaymentStatus())
                .paymentDate(LocalDate.now())
                .retryCount(0)
                .billing(billing)
                .build();

        PaymentEntity saved =
                paymentRepository.save(payment);

        PaymentDTO response = new PaymentDTO();

        response.setAmount(saved.getAmount());
        response.setPaymentMethod(saved.getPaymentMethod());
        response.setPaymentStatus(saved.getPaymentStatus());
        response.setBillingId(
                saved.getBilling().getBillingId());

        return response;
    }
}
