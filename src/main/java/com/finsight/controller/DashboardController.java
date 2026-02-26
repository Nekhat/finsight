package com.finsight.controller;

import com.finsight.dto.DashboardRequest;
import com.finsight.dto.DashboardResponse;
import com.finsight.service.DashboardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(
            @PathVariable Integer userId,
            @Valid DashboardRequest request) {

       DashboardResponse dashboardResponse =
               dashboardService.getDashboard(userId, request.getMonth(), request.getYear());

        return ResponseEntity.ok(dashboardResponse);

    }

}
