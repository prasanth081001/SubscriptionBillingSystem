package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.CustomerDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);

    Page<CustomerDTO> searchCustomers(
            String name,
            int page,
            int size
    );

    CustomerDTO getCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO dto);

    void softDeleteCustomer(Long id);
}
