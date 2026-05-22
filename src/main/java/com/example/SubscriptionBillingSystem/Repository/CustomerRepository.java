package com.example.SubscriptionBillingSystem.Repository;

import com.example.SubscriptionBillingSystem.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Page<Customer> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

}
