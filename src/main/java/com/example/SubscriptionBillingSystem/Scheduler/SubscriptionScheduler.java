package com.example.SubscriptionBillingSystem.Scheduler;

import com.example.SubscriptionBillingSystem.Entity.SubscriptionEntity;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionRepository;
import com.example.SubscriptionBillingSystem.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionScheduler {
    private final SubscriptionRepository subscriptionRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiredSubscriptions() {

        List<SubscriptionEntity> subscriptions =
                subscriptionRepository.findAll();

        for (SubscriptionEntity subscription : subscriptions) {

            if (subscription.getEndDate()
                    .isBefore(LocalDate.now())) {

                subscription.setStatus(
                        AppConstants.EXPIRED);

                subscriptionRepository.save(subscription);

                log.info(
                        "Subscription expired : {}",
                        subscription.getSubscriptionId()
                );
            }
        }
    }
}
