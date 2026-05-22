package com.example.SubscriptionBillingSystem.ServiceImpl;

import com.example.SubscriptionBillingSystem.DTO.PlanDTO;
import com.example.SubscriptionBillingSystem.Entity.Plan;
import com.example.SubscriptionBillingSystem.Repository.PlanRepository;
import com.example.SubscriptionBillingSystem.Service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public PlanDTO createPlan(PlanDTO dto) {

        Plan plan = Plan.builder()
                .planName(dto.getPlanName())
                .price(dto.getPrice())
                .billingCycle(dto.getBillingCycle())
                .maxUsers(dto.getMaxUsers())
                .maxStorage(dto.getMaxStorage())
                .status(dto.getStatus())
                .build();

        Plan saved = planRepository.save(plan);

        PlanDTO response = new PlanDTO();

        response.setPlanName(saved.getPlanName());
        response.setPrice(saved.getPrice());
        response.setBillingCycle(saved.getBillingCycle());
        response.setMaxUsers(saved.getMaxUsers());
        response.setMaxStorage(saved.getMaxStorage());
        response.setStatus(saved.getStatus());

        return response;
    }

    @Override
    public List<PlanDTO> getAllPlans() {

        return planRepository.findAll()
                .stream()
                .map(plan -> {

                    PlanDTO dto = new PlanDTO();

                    dto.setPlanName(plan.getPlanName());
                    dto.setPrice(plan.getPrice());
                    dto.setBillingCycle(plan.getBillingCycle());
                    dto.setMaxUsers(plan.getMaxUsers());
                    dto.setMaxStorage(plan.getMaxStorage());
                    dto.setStatus(plan.getStatus());

                    return dto;

                }).collect(Collectors.toList());
    }

    @Override
    public PlanDTO updatePlan(Long id, PlanDTO dto) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Plan not found"));

        plan.setPlanName(dto.getPlanName());
        plan.setPrice(dto.getPrice());
        plan.setBillingCycle(dto.getBillingCycle());
        plan.setMaxUsers(dto.getMaxUsers());
        plan.setMaxStorage(dto.getMaxStorage());
        plan.setStatus(dto.getStatus());

        Plan updated = planRepository.save(plan);

        PlanDTO response = new PlanDTO();

        response.setPlanName(updated.getPlanName());
        response.setPrice(updated.getPrice());
        response.setBillingCycle(updated.getBillingCycle());
        response.setMaxUsers(updated.getMaxUsers());
        response.setMaxStorage(updated.getMaxStorage());
        response.setStatus(updated.getStatus());

        return response;
    }

    @Override
    public void deletePlan(Long id) {

        planRepository.deleteById(id);
    }
}
