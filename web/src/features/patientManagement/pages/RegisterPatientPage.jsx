import { useNavigate } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";
import DashboardCard from "../../../components/dashboard/DashboardCard";

import PatientForm from "../components/PatientForm";

import { registerPatient } from "../services/patientService";

export default function RegisterPatientPage() {

    const navigate = useNavigate();

    const handleSubmit = async (patient) => {

        try {

            await registerPatient(patient);

            navigate("/staff/patients");

        } catch (error) {

            console.error(
                "Failed registering patient:",
                error
            );

        }

    };

    return (

        <>

            <PageHeader
                title="Register Patient"
                subtitle="Create a new patient record"
            />

            <DashboardCard>

                <PatientForm
                    onSubmit={handleSubmit}
                />

            </DashboardCard>

        </>

    );

}