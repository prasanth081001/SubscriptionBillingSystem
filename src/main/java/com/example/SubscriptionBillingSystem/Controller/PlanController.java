package com.example.SubscriptionBillingSystem.Controller;

import com.example.SubscriptionBillingSystem.DTO.PlanDTO;
import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import com.example.SubscriptionBillingSystem.Service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping
    public ApiResponse<PlanDTO> createPlan(
            @RequestBody PlanDTO dto
    ) {

        return ApiResponse.<PlanDTO>builder()
                .status(true)
                .message("Plan created successfully")
                .data(planService.createPlan(dto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PlanDTO>> getAllPlans() {

        return ApiResponse.<List<PlanDTO>>builder()
                .status(true)
                .message("Plans fetched successfully")
                .data(planService.getAllPlans())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PlanDTO> updatePlan(
            @PathVariable Long id,
            @RequestBody PlanDTO dto
    ) {

        return ApiResponse.<PlanDTO>builder()
                .status(true)
                .message("Plan updated successfully")
                .data(planService.updatePlan(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePlan(
            @PathVariable Long id
    ) {

        planService.deletePlan(id);

        return ApiResponse.<String>builder()
                .status(true)
                .message("Plan deleted successfully")
                .data("Deleted")
                .build();
    }
}
