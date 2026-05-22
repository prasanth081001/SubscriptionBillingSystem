package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.PaymentDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentDTO> makePayment(@RequestBody PaymentDTO dto){
        return ApiResponse.<PaymentDTO>builder().status(true)
                .message("Payment completed successfully")
                .data(paymentService.makePayment(dto)).build();
    }
}
