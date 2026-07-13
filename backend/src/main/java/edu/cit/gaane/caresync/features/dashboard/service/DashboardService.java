package edu.cit.gaane.caresync.features.dashboard.service;
import org.springframework.stereotype.Service;
import edu.cit.gaane.caresync.features.authentication.repository.UserRepository;
import edu.cit.gaane.caresync.features.dashboard.dto.DashboardStatsResponse;


@Service
public class DashboardService {
    private final UserRepository userRepository;


    public DashboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DashboardStatsResponse getDashboardStats() {

        long totalUsers = userRepository.count();

        long totalDoctors = userRepository.countByRoleIgnoreCase("Doctor");

        long totalStaff = userRepository.countByRoleIgnoreCase("Clinic Staff");

        long inactiveUsers = userRepository.countByActiveFalse();

        return new DashboardStatsResponse(
                totalUsers,
                totalDoctors,
                totalStaff,
                inactiveUsers
        );

    }
    
}
