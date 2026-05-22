package com.example.SubscriptionBillingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "billing")
public class BillingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    private String invoiceNumber;

    private Double amount;

    private Double gstAmount;

    private Double finalAmount;

    private LocalDate billingDate;

    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;
}
