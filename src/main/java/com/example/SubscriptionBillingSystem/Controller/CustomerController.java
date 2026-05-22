package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.CustomerDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerDTO> createCustomer(
            @RequestBody CustomerDTO dto
    ) {

        return ApiResponse.<CustomerDTO>builder()
                .status(true)
                .message("Customer created successfully")
                .data(customerService.createCustomer(dto))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerDTO> getCustomerById(
            @PathVariable Long id
    ) {

        return ApiResponse.<CustomerDTO>builder()
                .status(true)
                .message("Customer fetched successfully")
                .data(customerService.getCustomerById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO dto
    ) {

        return ApiResponse.<CustomerDTO>builder()
                .status(true)
                .message("Customer updated successfully")
                .data(customerService.updateCustomer(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> softDeleteCustomer(
            @PathVariable Long id
    ) {

        customerService.softDeleteCustomer(id);

        return ApiResponse.<String>builder()
                .status(true)
                .message("Customer deleted successfully")
                .data("Deleted")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<Page<CustomerDTO>> searchCustomers(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return ApiResponse.<Page<CustomerDTO>>builder()
                .status(true)
                .message("Customers fetched successfully")
                .data(customerService.searchCustomers(
                        name,
                        page,
                        size
                ))
                .build();
    }
}
