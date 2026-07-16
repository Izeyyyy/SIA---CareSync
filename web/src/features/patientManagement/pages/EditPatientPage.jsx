import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";
import DashboardCard from "../../../components/dashboard/DashboardCard";

import PatientForm from "../components/PatientForm";

import patientService from "../services/patientService";


export default function EditPatientPage() {

    const { id } = useParams();

    const navigate = useNavigate();


    const [patient, setPatient] = useState(null);



    const loadPatient = async () => {

    try {

        const patient = await patientService.getPatient(id);

        setPatient(patient);

        } catch (error) {

            console.error(
                "Failed loading patient:",
                error
            );

        }

    };



    useEffect(() => {

        loadPatient();

    }, []);



    const handleSubmit = async (updatedPatient) => {

    try {

        await patientService.updatePatient(
            id,
            updatedPatient
        );

        navigate("/staff/patients");

            } catch (error) {

                console.error(
                    "Failed updating patient:",
                    error
                );

            }

        };



    if (!patient) {

        return (

            <p>
                Loading patient...
            </p>

        );

    }



    return (

        <>

            <PageHeader

                title="Edit Patient"

                subtitle="Update patient demographic information"

            />


            <DashboardCard>

                <PatientForm

                    initialData={patient}

                    onSubmit={handleSubmit}

                />

            </DashboardCard>


        </>

    );

}