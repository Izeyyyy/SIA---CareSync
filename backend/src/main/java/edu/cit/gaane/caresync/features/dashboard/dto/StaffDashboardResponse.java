package edu.cit.gaane.caresync.features.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffDashboardResponse {

    private long totalPatients;

    private long newPatientsThisMonth;

    private long malePatients;

    private long femalePatients;

}