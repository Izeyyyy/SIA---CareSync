package edu.cit.gaane.caresync.features.dashboard.service;


import java.time.LocalDateTime;
import java.time.YearMonth;

import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.features.dashboard.dto.StaffDashboardResponse;
import edu.cit.gaane.caresync.features.patientRegistration.repository.PatientRepository;


@Service
public class StaffDashboardService {

    private final PatientRepository patientRepository;

    public StaffDashboardService(
            PatientRepository patientRepository
    ){
        this.patientRepository = patientRepository;

    }

    public StaffDashboardResponse getStatistics(){

        YearMonth currentMonth =
                YearMonth.now();

        LocalDateTime startOfMonth =
                currentMonth
                .atDay(1)
                .atStartOfDay();

        long totalPatients =
                patientRepository.count();

        long newPatients =
                patientRepository
                .countByDateRegisteredAfter(
                        startOfMonth
                );

        long malePatients =
                patientRepository
                .countByGenderIgnoreCase(
                        "Male"
                );

        long femalePatients =
                patientRepository
                .countByGenderIgnoreCase(
                        "Female"
                );


        return new StaffDashboardResponse(
                totalPatients,
                newPatients,
                malePatients,
                femalePatients

        );
    }
}
