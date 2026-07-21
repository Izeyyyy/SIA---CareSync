package edu.cit.gaane.caresync.features.dashboard.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.features.dashboard.dto.StaffDashboardResponse;
import edu.cit.gaane.caresync.features.dashboard.service.StaffDashboardService;


@RestController
@RequestMapping("/api/staff/dashboard")
public class StaffDashboardController {


    private final StaffDashboardService service;


    public StaffDashboardController(
            StaffDashboardService service
    ){

        this.service = service;

    }



    @GetMapping
    public StaffDashboardResponse getDashboard(){

        return service.getStatistics();

    }

}