package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.BillingDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping("/generate")
    public ApiResponse<BillingDTO> generateInvoice(
            @RequestBody BillingDTO dto
    ) {

        return ApiResponse.<BillingDTO>builder()
                .status(true)
                .message("Invoice generated successfully")
                .data(billingService.generateInvoice(dto))
                .build();
    }
}
