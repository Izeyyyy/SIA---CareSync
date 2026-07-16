import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";
import DashboardCard from "../../../components/dashboard/DashboardCard";
import ActionBar from "../../../components/common/ActionBar";
import EmptyState from "../../../components/common/EmptyState";

import PatientTable from "../components/PatientTable";
import patientService from "../services/patientService";

export default function PatientListPage() {

    const navigate = useNavigate();

    const [patients, setPatients] = useState([]);
    const [search, setSearch] = useState("");

    const loadPatients = async () => {

        try {

            const patients = await patientService.getPatients();
            console.log("FULL RESPONSE:", patients);
            console.log("DATA:", patients);
            console.log("ARRAY?:", Array.isArray(patients));
            setPatients(patients);

        } catch (error) {

            console.error(
                "Failed loading patients:",
                error
            );

        }

    };

    useEffect(() => {

            loadPatients();

        }, []);

        const filteredPatients = patients.filter(patient => {

        const fullName =
            `${patient.firstName} ${patient.lastName}`.toLowerCase();

        return (

            fullName.includes(search.toLowerCase()) ||

            patient.patientNumber
                .toLowerCase()
                .includes(search.toLowerCase())

        );

    });

    return (

            <>
    <PageHeader
        title="Patients"
        subtitle="Register and manage patient records"
    />

    <DashboardCard>

        <ActionBar

            title="Patient List"

            buttonLabel="+ Register Patient"

            onButtonClick={() =>
                navigate("/staff/patients/register")
            }

            searchValue = {search}
            onSearchChange = {setSearch}

        />

        {

        filteredPatients.length === 0 ?

        (

            <EmptyState

            title="No Patients Found"

            description="No patient records have been registered yet. Register your first patient to begin managing clinic records."

            actionLabel="Register Patient"

            onAction={() =>
                navigate("/staff/patients/register")
            }

        />

        )

        :

        (


        <PatientTable
            patients={filteredPatients}
        />

        )
    }

    </DashboardCard>

        </>

    );

}