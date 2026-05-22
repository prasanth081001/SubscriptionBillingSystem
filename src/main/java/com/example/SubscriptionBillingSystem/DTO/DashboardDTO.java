package com.example.SubscriptionBillingSystem.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    private Double totalRevenue;

    private Long activeSubscriptions;

    private Long expiredSubscriptions;

    private Long totalCustomers;

    private Double monthlyRevenue;
}
