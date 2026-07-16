import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";
import DashboardCard from "../../../components/dashboard/DashboardCard";
import PrimaryButton from "../../../components/common/PrimaryButton";
import SecondaryButton from "../../../components/common/SecondaryButton";

import patientService from "../services/patientService";


export default function PatientRecordsPage() {

    const { id } = useParams();

    const [patient, setPatient] = useState(null);
    const navigate = useNavigate();


    const loadPatient = async () => {

        try {

            const response = await patientService.getPatient(id);

            setPatient(response.data);

        } catch(error) {

            console.error(
                "Failed loading patient:",
                error
            );

        }

    };


    useEffect(() => {

        loadPatient();

    }, []);



    if (!patient) {

        return (
            <p>
                Loading patient...
            </p>
        );

    }


    const age = Math.floor(
        (
            new Date() -
            new Date(patient.birthDate)
        )
        /
        (1000 * 60 * 60 * 24 * 365.25)
    );


    return (

        <>

        <PageHeader

            title="Patient Record"

            subtitle="Patient demographic information"

        />


        <DashboardCard>


            <div className="patient-details">


                <p>
                    <strong>
                        Patient Number:
                    </strong>

                    {patient.patientNumber}

                </p>


                <p>
                    <strong>
                        Name:
                    </strong>

                    {patient.lastName},{" "}
                    {patient.firstName}{" "}
                    {patient.middleInitial}.

                </p>


                <p>
                    <strong>
                        Birth Date:
                    </strong>

                    {patient.birthDate}

                </p>


                <p>
                    <strong>
                        Age:
                    </strong>

                    {age}

                </p>


                <p>
                    <strong>
                        Gender:
                    </strong>

                    {patient.gender}

                </p>


                <p>
                    <strong>
                        Contact:
                    </strong>

                    {patient.contactNumber}

                </p>


                <p>
                    <strong>
                        Address:
                    </strong>

                    {patient.address}

                </p>


                <p>
                    <strong>
                        Registered:
                    </strong>

                    {new Date(
                        patient.dateRegistered
                    ).toLocaleDateString()}

                </p>


            </div>

            <div className="form-actions">

        <SecondaryButton
            onClick={() =>
                navigate("/staff/patients")
            }
        >
            Back
        </SecondaryButton>


        <PrimaryButton
            onClick={() =>
                navigate(`/staff/patients/${id}/edit`)
            }
        >
            Edit Patient
        </PrimaryButton>

    </div>



        </DashboardCard>


        </>

    );

}