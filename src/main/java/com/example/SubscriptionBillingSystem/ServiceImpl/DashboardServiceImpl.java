package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.DashboardDTO;
import com.example.SubscriptionBillingSystem.Repository.BillingRepository;
import com.example.SubscriptionBillingSystem.Repository.CustomerRepository;
import com.example.SubscriptionBillingSystem.Repository.PaymentRepository;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionRepository;
import com.example.SubscriptionBillingSystem.Service.DashboardService;
import com.example.SubscriptionBillingSystem.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final BillingRepository billingRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final CustomerRepository customerRepository;

    private final PaymentRepository paymentRepository;

    @Override
    public DashboardDTO getDashboardData() {

        Double totalRevenue =
                billingRepository.getTotalRevenue();

        Double monthlyRevenue =
                paymentRepository.getMonthlyRevenue();

        Long activeSubscriptions =
                subscriptionRepository.countByStatus(
                        AppConstants.ACTIVE
                );

        Long expiredSubscriptions =
                subscriptionRepository.countByStatus(
                        AppConstants.EXPIRED
                );

        Long totalCustomers =
                customerRepository.count();

        return DashboardDTO.builder()
                .totalRevenue(
                        totalRevenue == null ? 0 : totalRevenue
                )
                .monthlyRevenue(
                        monthlyRevenue == null ? 0 : monthlyRevenue
                )
                .activeSubscriptions(activeSubscriptions)
                .expiredSubscriptions(expiredSubscriptions)
                .totalCustomers(totalCustomers)
                .build();
    }
}
