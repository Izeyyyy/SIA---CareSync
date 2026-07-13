import "../../styles/dashboard.css";

import PageHeader from "../../components/dashboard/PageHeader";
import StatCard from "../../components/dashboard/StatCard";

export default function ClinicStaffDashboard() {

    return (

        <>

            <PageHeader
                title="Clinic Staff Dashboard"
                subtitle="Manage patients and clinic operations"
            />

            <div className="dashboard-stats">

                <StatCard
                    title="Registered Patients"
                    value="0"
                />

                <StatCard
                    title="New Patients This Month"
                    value="0"
                />

                <StatCard
                    title="Male Patients"
                    value="0"
                />

                <StatCard
                    title="Female Patients"
                    value="0"
                />

            </div>

        </>

    );

}