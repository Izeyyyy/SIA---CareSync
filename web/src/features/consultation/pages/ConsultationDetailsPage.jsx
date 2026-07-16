import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import DashboardLayout from "../../../components/dashboard/DashboardLayout";
import PageHeader from "../../../components/dashboard/PageHeader";

import SecondaryButton from "../../../components/common/SecondaryButton";
import PrimaryButton from "../../../components/common/PrimaryButton";

import consultationService from "../services/consultationService";

export default function ConsultationDetailsPage(){

    const { id } = useParams();

    const navigate = useNavigate();

    const [consultation, setConsultation] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadConsultation();

    }, []);

    async function loadConsultation(){

        try{

            const data =
                await consultationService.getConsultationById(id);

            setConsultation(data);

        }

        catch(error){

            console.error(error);

        }

        finally{

            setLoading(false);

        }

    }

    if(loading){

        return <p>Loading...</p>;

    }

    if(!consultation){

        return <p>Consultation not found.</p>;

    }

    return(

        <>

            <PageHeader

                title="Consultation Details"

                subtitle={consultation.patientName}

            />

            <div className="card">

                <h3>General Information</h3>

                <p><strong>Patient:</strong> {consultation.patientName}</p>

                <p><strong>Doctor:</strong> {consultation.doctorName}</p>

                <p>
                    <strong>Date:</strong>{" "}
                    {new Date(
                        consultation.consultationDate
                    ).toLocaleString()}
                </p>

                <hr />

                <p>

                    <strong>Chief Complaint</strong>

                </p>

                <p>{consultation.chiefComplaint}</p>

                <p>

                    <strong>Diagnosis</strong>

                </p>

                <p>{consultation.diagnosis}</p>

                <p>

                    <strong>Treatment Plan</strong>

                </p>

                <p>{consultation.treatmentPlan}</p>

                <p>

                    <strong>Prescription</strong>

                </p>

                <p>{consultation.prescription}</p>

                <p>

                    <strong>Notes</strong>

                </p>

                <p>{consultation.notes}</p>

                <div
                    style={{
                        display:"flex",
                        gap:"10px",
                        marginTop:"20px"
                    }}
                >

                    <PrimaryButton

                        onClick={()=>

                            navigate(
                                `/doctor/consultations/edit/${consultation.id}`
                            )

                        }

                    >

                        Edit Consultation

                    </PrimaryButton>

                    <SecondaryButton

                        onClick={()=>

                            navigate(-1)

                        }

                    >

                        Back

                    </SecondaryButton>

                </div>

            </div>

        </>

    );

}