package com.example.SubscriptionBillingSystem.Repository;

import com.example.SubscriptionBillingSystem.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    @Query("""
       SELECT SUM(p.amount)
       FROM PaymentEntity p
       WHERE MONTH(p.paymentDate)=MONTH(CURRENT_DATE)
       """)
    Double getMonthlyRevenue();
}
