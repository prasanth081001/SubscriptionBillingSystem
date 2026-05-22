package com.example.SubscriptionBillingSystem.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T>{
    private boolean status;
    private String message;
    private T data;
}
