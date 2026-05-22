package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.SubscriptionHistoryDTO;
import com.example.SubscriptionBillingSystem.Entity.SubscriptionHistory;
import com.example.SubscriptionBillingSystem.Repository.SubscriptionHistoryRepository;
import com.example.SubscriptionBillingSystem.Service.SubscriptionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SubscriptionHistoryServiceImpl implements SubscriptionHistoryService {
    private final SubscriptionHistoryRepository historyRepository;

    @Override
    public List<SubscriptionHistoryDTO>
    getHistoryBySubscription(Long subscriptionId) {

        List<SubscriptionHistory> historyList =
                historyRepository
                        .findBySubscriptionSubscriptionId(subscriptionId);

        return historyList.stream().map(history -> {

            SubscriptionHistoryDTO dto =
                    new SubscriptionHistoryDTO();

            dto.setActionType(history.getActionType());
            dto.setOldValue(history.getOldValue());
            dto.setNewValue(history.getNewValue());
            dto.setRemarks(history.getRemarks());
            dto.setActionTime(history.getActionTime());

            return dto;

        }).collect(Collectors.toList());
    }
}
