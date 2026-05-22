package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionHistoryDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.SubscriptionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class SubscriptionHistoryController {
    private final SubscriptionHistoryService historyService;

    @GetMapping("/{subscriptionId}")
    public ApiResponse<List<SubscriptionHistoryDTO>>
    getHistory(
            @PathVariable Long subscriptionId
    ) {

        return ApiResponse
                .<List<SubscriptionHistoryDTO>>builder()
                .status(true)
                .message("History fetched successfully")
                .data(
                        historyService.getHistoryBySubscription(
                                subscriptionId
                        )
                )
                .build();
    }

}
