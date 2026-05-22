package com.example.SubscriptionBillingSystem.Repository;

import com.example.SubscriptionBillingSystem.Entity.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory,Long> {
    List<SubscriptionHistory> findBySubscriptionSubscriptionId(Long subscription);
}
