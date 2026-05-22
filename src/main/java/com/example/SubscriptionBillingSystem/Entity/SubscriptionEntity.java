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
@Table(name = "subscriptions")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    private String planName;

    private Double amount;

    private String billingCycle;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate nextBillingDate;

    private Boolean autoRenew;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
