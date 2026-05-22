package com.example.SubscriptionBillingSystem.Repository;

import com.example.SubscriptionBillingSystem.Entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillingRepository extends JpaRepository<BillingEntity,Long> {
    @Query("SELECT SUM(b.finalAmount) FROM BillingEntity b") Double getTotalRevenue();
}
