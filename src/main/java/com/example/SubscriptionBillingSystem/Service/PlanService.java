package com.example.SubscriptionBillingSystem.Service;

import com.example.SubscriptionBillingSystem.DTO.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO createPlan(PlanDTO dto);
    List<PlanDTO> getAllPlans();
    PlanDTO updatePlan(Long id,PlanDTO dto);
    void deletePlan(Long id);
}
