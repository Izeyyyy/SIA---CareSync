import "../../styles/dashboard.css";
import { useEffect, useState } from "react";
import { getDashboardStats } from "./services/DashboardService";

import SideBar from "../../components/dashboard/Sidebar";
import TopNavbar from "../../components/dashboard/TopNavbar";
import DashboardLayout from "../../components/dashboard/DashboardLayout";
import PageHeader from "../../components/dashboard/PageHeader";
import StatCard from "../../components/dashboard/StatCard";
import UserManagement from "../userManagement/UserManagement";


export default function AdminDashboard(){

    const [stats, setStats] = useState({

    totalUsers: 0,
    totalDoctors: 0,
    totalStaff: 0,
    inactiveUsers: 0

});

const loadStats = async () => {

    try {

        const response = await getDashboardStats();

        setStats(response.data);

    } catch (err) {

        console.error("Failed loading dashboard stats:", err);

    }

};

useEffect(() => {

    loadStats();

}, []);

    return (
        <>

            <PageHeader
                title="Admin Dashboard"
                subtitle="Manage users and system access"
            />


            <div className="dashboard-stats">

                <StatCard
                    title="Total Users"
                    value={stats.totalUsers}
                />

                <StatCard
                    title="Doctors"
                    value={stats.totalDoctors}
                />

                <StatCard
                    title="Clinic Staff"
                    value={stats.totalStaff}
                />

                <StatCard
                    title="Inactive Accounts"
                    value={stats.inactiveUsers}
                />

            </div>

            <UserManagement onUserUpdated={loadStats}/>
        </>
    );
}