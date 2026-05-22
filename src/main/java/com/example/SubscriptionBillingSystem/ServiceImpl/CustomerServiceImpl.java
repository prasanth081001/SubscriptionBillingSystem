package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.CustomerDTO;
import com.example.SubscriptionBillingSystem.Entity.Customer;
import com.example.SubscriptionBillingSystem.Repository.CustomerRepository;
import com.example.SubscriptionBillingSystem.Service.CustomerService;
import com.example.SubscriptionBillingSystem.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerDTO createCustomer(CustomerDTO dto) {

        Customer customer = Customer.builder()
                .companyId(dto.getCompanyId())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .address(dto.getAddress())
                .status(AppConstants.ACTIVE)
                .build();

        Customer saved = customerRepository.save(customer);

        log.info("Customer created successfully");

        CustomerDTO response = new CustomerDTO();

        response.setCompanyId(saved.getCompanyId());
        response.setFullName(saved.getFullName());
        response.setEmail(saved.getEmail());
        response.setMobile(saved.getMobile());
        response.setAddress(saved.getAddress());
        response.setStatus(saved.getStatus());

        return response;
    }


    public Page<CustomerDTO> searchCustomers(
            String name,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return customerRepository
                .findByFullNameContainingIgnoreCase(name, pageable)
                .map(customer -> {

                    CustomerDTO dto = new CustomerDTO();

                    dto.setCompanyId(customer.getCompanyId());
                    dto.setFullName(customer.getFullName());
                    dto.setEmail(customer.getEmail());
                    dto.setMobile(customer.getMobile());
                    dto.setAddress(customer.getAddress());
                    dto.setStatus(customer.getStatus());

                    return dto;
                });
    }

    public CustomerDTO getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        CustomerDTO dto = new CustomerDTO();

        dto.setCompanyId(customer.getCompanyId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setMobile(customer.getMobile());
        dto.setAddress(customer.getAddress());
        dto.setStatus(customer.getStatus());

        return dto;
    }


    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setMobile(dto.getMobile());
        customer.setAddress(dto.getAddress());

        Customer updated = customerRepository.save(customer);

        CustomerDTO response = new CustomerDTO();

        response.setCompanyId(updated.getCompanyId());
        response.setFullName(updated.getFullName());
        response.setEmail(updated.getEmail());
        response.setMobile(updated.getMobile());
        response.setAddress(updated.getAddress());
        response.setStatus(updated.getStatus());

        return response;
    }

    @Override
    public void softDeleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customer.setStatus(AppConstants.CANCELLED);

        customerRepository.save(customer);

        log.info("Customer soft deleted");
    }
}
