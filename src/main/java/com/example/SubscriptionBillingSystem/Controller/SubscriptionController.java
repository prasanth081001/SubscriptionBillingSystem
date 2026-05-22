package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ApiResponse<SubscriptionDTO> createSubscription(
            @RequestBody SubscriptionDTO dto
    ) {

        return ApiResponse.<SubscriptionDTO>builder()
                .status(true)
                .message("Subscription created successfully")
                .data(subscriptionService.createSubscription(dto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SubscriptionDTO>>
    getAllSubscriptions() {

        return ApiResponse.<List<SubscriptionDTO>>builder()
                .status(true)
                .message("Subscriptions fetched successfully")
                .data(subscriptionService.getAllSubscriptions())
                .build();
    }

    @PutMapping("/upgrade/{id}")
    public ApiResponse<SubscriptionDTO> upgradePlan(
            @PathVariable Long id,
            @RequestParam String newPlan,
            @RequestParam Double newAmount
    ) {

        return ApiResponse.<SubscriptionDTO>builder()
                .status(true)
                .message("Subscription upgraded successfully")
                .data(subscriptionService.upgradePlan(
                        id,
                        newPlan,
                        newAmount
                ))
                .build();
    }

    @PutMapping("/pause/{id}")
    public ApiResponse<SubscriptionDTO> pauseSubscription(
            @PathVariable Long id
    ) {

        return ApiResponse.<SubscriptionDTO>builder()
                .status(true)
                .message("Subscription paused successfully")
                .data(subscriptionService.pauseSubscription(id))
                .build();
    }

    @PutMapping("/resume/{id}")
    public ApiResponse<SubscriptionDTO> resumeSubscription(
            @PathVariable Long id
    ) {

        return ApiResponse.<SubscriptionDTO>builder()
                .status(true)
                .message("Subscription resumed successfully")
                .data(subscriptionService.resumeSubscription(id))
                .build();
    }

    @GetMapping("/expired")
    public ApiResponse<List<SubscriptionDTO>>
    getExpiredSubscriptions() {

        return ApiResponse.<List<SubscriptionDTO>>builder()
                .status(true)
                .message("Expired subscriptions fetched")
                .data(subscriptionService.getExpiredSubscriptions())
                .build();
    }
}
