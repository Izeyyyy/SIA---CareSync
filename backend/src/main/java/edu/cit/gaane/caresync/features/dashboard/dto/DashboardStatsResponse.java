package edu.cit.gaane.caresync.features.dashboard.dto;

public class DashboardStatsResponse {

    private long totalUsers;
    private long totalDoctors;
    private long totalStaff;
    private long inactiveUsers;

    public DashboardStatsResponse(
            long totalUsers,
            long totalDoctors,
            long totalStaff,
            long inactiveUsers
    ) {
        this.totalUsers = totalUsers;
        this.totalDoctors = totalDoctors;
        this.totalStaff = totalStaff;
        this.inactiveUsers = inactiveUsers;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalDoctors() {
        return totalDoctors;
    }

    public long getTotalStaff() {
        return totalStaff;
    }

    public long getInactiveUsers() {
        return inactiveUsers;
    }
}