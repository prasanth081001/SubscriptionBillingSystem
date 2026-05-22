package com.example.SubscriptionBillingSystem.Repository;

import com.example.SubscriptionBillingSystem.Entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.plaf.ListUI;
import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,Long> {
    List<SubscriptionEntity> findByEndDateBefore(LocalDate date);
    Long countByStatus(String status);
}
