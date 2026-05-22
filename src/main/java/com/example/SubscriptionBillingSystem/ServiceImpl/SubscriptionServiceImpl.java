package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionDTO;
import com.example.SubscriptionBillingSystem.Entity.Customer;
import com.example.SubscriptionBillingSystem.Entity.SubscriptionEntity;
import com.example.SubscriptionBillingSystem.Entity.SubscriptionHistory;
import com.example.SubscriptionBillingSystem.Repository.CustomerRepository;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionHistoryRepository;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionRepository;
import com.example.SubscriptionBillingSystem.Service.SubscriptionService;
import com.example.SubscriptionBillingSystem.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final CustomerRepository customerRepository;

    private final SubscriptionHistoryRepository historyRepository;


    public SubscriptionDTO createSubscription(SubscriptionDTO dto) {

        Customer customer =
                customerRepository.findById(dto.getCustomerId())
                        .orElseThrow(() ->
                                new RuntimeException("Customer not found"));

        LocalDate nextBillingDate;

        switch (dto.getBillingCycle()) {

            case "MONTHLY"->
                nextBillingDate = dto.getStartDate().plusMonths(1);


            case "QUARTERLY"->
                nextBillingDate = dto.getStartDate().plusMonths(3);


            case "YEARLY"->
                nextBillingDate = dto.getStartDate().plusYears(1);


            default->
               throw new RuntimeException("Invalid billing cycle");
        }

        SubscriptionEntity subscription = SubscriptionEntity.builder()
                .planName(dto.getPlanName())
                .amount(dto.getAmount())
                .billingCycle(dto.getBillingCycle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .nextBillingDate(nextBillingDate)
                .autoRenew(dto.getAutoRenew())
                .status(AppConstants.ACTIVE)
                .customer(customer)
                .build();

        SubscriptionEntity saved =
                subscriptionRepository.save(subscription);

        SubscriptionHistory history =
                SubscriptionHistory.builder()
                        .actionType("SUBSCRIPTION_CREATED")
                        .oldValue("N/A")
                        .newValue(saved.getPlanName())
                        .remarks("New subscription created")
                        .actionTime(LocalDateTime.now())
                        .subscription(saved)
                        .build();

        historyRepository.save(history);

        log.info("Subscription created successfully");

        SubscriptionDTO response = new SubscriptionDTO();

        response.setPlanName(saved.getPlanName());
        response.setAmount(saved.getAmount());
        response.setBillingCycle(saved.getBillingCycle());
        response.setStartDate(saved.getStartDate());
        response.setEndDate(saved.getEndDate());
        response.setAutoRenew(saved.getAutoRenew());
        response.setStatus(saved.getStatus());
        response.setCustomerId(
                saved.getCustomer().getCustomerId());

        return response;
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {

        return subscriptionRepository.findAll()
                .stream()
                .map(subscription -> {

                    SubscriptionDTO dto =
                            new SubscriptionDTO();

                    dto.setPlanName(subscription.getPlanName());
                    dto.setAmount(subscription.getAmount());
                    dto.setBillingCycle(
                            subscription.getBillingCycle());
                    dto.setStartDate(subscription.getStartDate());
                    dto.setEndDate(subscription.getEndDate());
                    dto.setAutoRenew(subscription.getAutoRenew());
                    dto.setStatus(subscription.getStatus());
                    dto.setCustomerId(
                            subscription.getCustomer()
                                    .getCustomerId());

                    return dto;

                }).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO upgradePlan(
            Long subscriptionId,
            String newPlan,
            Double newAmount
    ) {

        SubscriptionEntity subscription =
                subscriptionRepository.findById(subscriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"));

        String oldPlan = subscription.getPlanName();

        subscription.setPlanName(newPlan);

        subscription.setAmount(newAmount);

        SubscriptionEntity updated =
                subscriptionRepository.save(subscription);

        SubscriptionHistory history =
                SubscriptionHistory.builder()
                        .actionType("PLAN_UPGRADED")
                        .oldValue(oldPlan)
                        .newValue(newPlan)
                        .remarks("Subscription upgraded")
                        .actionTime(LocalDateTime.now())
                        .subscription(updated)
                        .build();

        historyRepository.save(history);

        SubscriptionDTO dto = new SubscriptionDTO();

        dto.setPlanName(updated.getPlanName());
        dto.setAmount(updated.getAmount());
        dto.setBillingCycle(updated.getBillingCycle());
        dto.setStartDate(updated.getStartDate());
        dto.setEndDate(updated.getEndDate());
        dto.setAutoRenew(updated.getAutoRenew());
        dto.setStatus(updated.getStatus());
        dto.setCustomerId(
                updated.getCustomer().getCustomerId());

        return dto;
    }

    @Override
    public SubscriptionDTO pauseSubscription(
            Long subscriptionId
    ) {

        SubscriptionEntity subscription =
                subscriptionRepository.findById(subscriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"));

        subscription.setStatus(AppConstants.PAUSED);

        SubscriptionEntity updated =
                subscriptionRepository.save(subscription);

        SubscriptionHistory history =
                SubscriptionHistory.builder()
                        .actionType("SUBSCRIPTION_PAUSED")
                        .oldValue(AppConstants.ACTIVE)
                        .newValue(AppConstants.PAUSED)
                        .remarks("Subscription paused")
                        .actionTime(LocalDateTime.now())
                        .subscription(updated)
                        .build();

        historyRepository.save(history);

        SubscriptionDTO dto = new SubscriptionDTO();

        dto.setPlanName(updated.getPlanName());
        dto.setAmount(updated.getAmount());
        dto.setBillingCycle(updated.getBillingCycle());
        dto.setStartDate(updated.getStartDate());
        dto.setEndDate(updated.getEndDate());
        dto.setAutoRenew(updated.getAutoRenew());
        dto.setStatus(updated.getStatus());
        dto.setCustomerId(
                updated.getCustomer().getCustomerId());

        return dto;
    }

    @Override
    public SubscriptionDTO resumeSubscription(
            Long subscriptionId
    ) {

        SubscriptionEntity subscription =
                subscriptionRepository.findById(subscriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"));

        subscription.setStatus(AppConstants.ACTIVE);

        SubscriptionEntity updated =
                subscriptionRepository.save(subscription);

        SubscriptionHistory history =
                SubscriptionHistory.builder()
                        .actionType("SUBSCRIPTION_RESUMED")
                        .oldValue(AppConstants.PAUSED)
                        .newValue(AppConstants.ACTIVE)
                        .remarks("Subscription resumed")
                        .actionTime(LocalDateTime.now())
                        .subscription(updated)
                        .build();

        historyRepository.save(history);

        SubscriptionDTO dto = new SubscriptionDTO();

        dto.setPlanName(updated.getPlanName());
        dto.setAmount(updated.getAmount());
        dto.setBillingCycle(updated.getBillingCycle());
        dto.setStartDate(updated.getStartDate());
        dto.setEndDate(updated.getEndDate());
        dto.setAutoRenew(updated.getAutoRenew());
        dto.setStatus(updated.getStatus());
        dto.setCustomerId(
                updated.getCustomer().getCustomerId());

        return dto;
    }

    @Override
    public List<SubscriptionDTO> getExpiredSubscriptions() {

        List<SubscriptionEntity> expiredSubscriptions =
                subscriptionRepository
                        .findByEndDateBefore(LocalDate.now());

        return expiredSubscriptions.stream()
                .map(subscription -> {

                    SubscriptionDTO dto =
                            new SubscriptionDTO();

                    dto.setPlanName(subscription.getPlanName());
                    dto.setAmount(subscription.getAmount());
                    dto.setBillingCycle(
                            subscription.getBillingCycle());
                    dto.setStartDate(subscription.getStartDate());
                    dto.setEndDate(subscription.getEndDate());
                    dto.setAutoRenew(subscription.getAutoRenew());
                    dto.setStatus(subscription.getStatus());
                    dto.setCustomerId(
                            subscription.getCustomer()
                                    .getCustomerId());

                    return dto;

                }).collect(Collectors.toList());
    }
}
