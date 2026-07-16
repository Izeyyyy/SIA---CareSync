import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";
import DashboardCard from "../../../components/dashboard/DashboardCard";
import EmptyState from "../../../components/common/EmptyState";
import DataTable from "../../../components/common/DataTable";

import patientService from "../../patientManagement/services/patientService";


export default function DoctorPatientListPage(){

    const navigate = useNavigate();

    const [patients, setPatients] = useState([]);

    const [loading, setLoading] = useState(true);



    useEffect(()=>{

        loadPatients();

    },[]);



    async function loadPatients(){

        try{

            const data =
                await patientService.getPatients();

            setPatients(data);

        }
        catch(error){

            console.error(
                "Failed loading patients:",
                error
            );

        }
        finally{

            setLoading(false);

        }

    }



    return (

        <>

            <PageHeader

                title="Patients"

                subtitle="View patient consultation records"

            />


            <DashboardCard>


                {
                    loading ?

                    (

                        <p>
                            Loading patients...
                        </p>

                    )

                    :

                    patients.length === 0 ?

                    (

                        <EmptyState

                            title="No Patients Found"

                            description="There are currently no registered patients."

                        />

                    )

                    :

                    (

                        <DataTable

                            columns={[

                                "Patient No.",

                                "Name",

                                "Birth Date",

                                "Gender",

                                "Action"

                            ]}


                            data={patients}


                            renderRow={(patient)=>(

                                <tr key={patient.id}>


                                    <td>

                                        {patient.patientNumber}

                                    </td>


                                    <td>

                                        {patient.lastName},{" "}

                                        {patient.firstName}

                                        {" "}

                                        {patient.middleInitial}.

                                    </td>


                                    <td>

                                        {
                                            new Date(
                                                patient.birthDate
                                            )
                                            .toLocaleDateString()
                                        }

                                    </td>


                                    <td>

                                        {patient.gender}

                                    </td>


                                    <td>

                                        <button

                                            className="patient-action-btn"

                                            onClick={()=>{

                                                navigate(
                                                    `/doctor/consultations/patients/${patient.id}`
                                                );

                                            }}

                                        >

                                            View Records

                                        </button>

                                    </td>


                                </tr>

                            )}

                        />

                    )

                }


            </DashboardCard>


        </>

    );

}