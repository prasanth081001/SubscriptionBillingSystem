package com.example.SubscriptionBillingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String companyId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String mobile;

    private String address;

    private String status;
}
