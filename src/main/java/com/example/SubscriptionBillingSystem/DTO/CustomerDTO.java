package com.example.SubscriptionBillingSystem.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private String companyId;

    private String fullName;

    private String email;

    private String mobile;

    private String address;

    private String status;
}
