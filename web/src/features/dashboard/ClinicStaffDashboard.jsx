import { useEffect, useState } from "react";

import "../../styles/dashboard.css";

import PageHeader from "../../components/dashboard/PageHeader";
import StatCard from "../../components/dashboard/StatCard";
import QuickActions from "./components/QuickActions";

import {
    getClinicStaffDashboard
} from "./services/clinicStaffDashboardService";


export default function ClinicStaffDashboard() {


    const [stats, setStats] = useState({

        totalPatients: 0,

        newPatientsThisMonth: 0,

        malePatients: 0,

        femalePatients: 0

    });



    useEffect(() => {

        loadDashboard();

    }, []);



    async function loadDashboard() {

        try {

            const data =
                await getClinicStaffDashboard();


            setStats(data);

        }

        catch(error) {

            console.error(
                "Failed to load clinic staff dashboard:",
                error
            );

        }

    }



    return (

        <>

            <PageHeader
                title="Clinic Staff Dashboard"
                subtitle="Manage patients and clinic operations"
            />


            <div className="dashboard-stats">


                <StatCard
                    title="Registered Patients"
                    value={stats.totalPatients}
                />


                <StatCard
                    title="New Patients This Month"
                    value={stats.newPatientsThisMonth}
                />


                <StatCard
                    title="Male Patients"
                    value={stats.malePatients}
                />


                <StatCard
                    title="Female Patients"
                    value={stats.femalePatients}
                />


            </div>


            <QuickActions />


        </>

    );

}