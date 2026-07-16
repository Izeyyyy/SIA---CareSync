import { useEffect, useState } from "react";
import DashboardLayout from "../../components/dashboard/DashboardLayout";
import PageHeader from "../../components/dashboard/PageHeader";
import DashboardCard from "../../components/dashboard/DashboardCard";
import PatientTable from "../patientManagement/components/PatientTable";
import patientService from "../patientManagement/services/patientService";

export default function DoctorDashboard(){

    const [patients,setPatients]=useState([]);

    useEffect(()=>{
        loadPatients();
    },[]);

    async function loadPatients(){

        const data=await patientService.getPatients();

        setPatients(data);

    }

    return(

        <>

            <PageHeader
                title="Doctor Dashboard"
                subtitle="Manage consultations"
            />

            <div className="dashboard-cards">

                <DashboardCard
                    title="Patients"
                    value={patients.length}
                />

            </div>

            <PatientTable

                patients={patients}

                doctorMode={true}

            />

        </>

    );

}