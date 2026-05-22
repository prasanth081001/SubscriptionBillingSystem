package com.example.SubscriptionBillingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String paymentStatus;

    private Integer retryCount;

    @ManyToOne
    @JoinColumn(name = "billing_id")
    private BillingEntity billing;
}
