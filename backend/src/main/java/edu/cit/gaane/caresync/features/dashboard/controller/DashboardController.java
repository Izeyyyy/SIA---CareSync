package edu.cit.gaane.caresync.features.dashboard.controller;

import edu.cit.gaane.caresync.features.dashboard.dto.DashboardStatsResponse;
import edu.cit.gaane.caresync.features.dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public DashboardStatsResponse getDashboardStats() {

        return dashboardService.getDashboardStats();

    }

}
