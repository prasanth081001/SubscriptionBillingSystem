package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.DashboardDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardDTO> getDashboard() {

        return ApiResponse.<DashboardDTO>builder()
                .status(true)
                .message("Dashboard data fetched successfully")
                .data(dashboardService.getDashboardData())
                .build();
    }
}
