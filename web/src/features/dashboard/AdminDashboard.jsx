import "../../styles/dashboard.css";

import SideBar from "../../components/dashboard/Sidebar";
import TopNavbar from "../../components/dashboard/TopNavbar";
import DashboardLayout from "../../components/dashboard/DashboardLayout";
import PageHeader from "../../components/dashboard/PageHeader";
import StatCard from "../../components/dashboard/StatCard";
import UserManagement from "../userManagement/UserManagement";


export default function AdminDashboard(){

    return (
        <DashboardLayout>

            <PageHeader
                title="Admin Dashboard"
                subtitle="Manage users and system access"
            />


            <div className="dashboard-stats">

                <StatCard
                    title="Total Users"
                    value="0"
                />

                <StatCard
                    title="Doctors"
                    value="0"
                />

                <StatCard
                    title="Clinic Staff"
                    value="0"
                />

                <StatCard
                    title="Inactive Accounts"
                    value="0"
                />

            </div>

            <UserManagement />
        </DashboardLayout>
    );
}