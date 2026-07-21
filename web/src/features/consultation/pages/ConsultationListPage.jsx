import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import PageHeader from "../../../components/dashboard/PageHeader";

import PrimaryButton from "../../../components/common/PrimaryButton";
import SecondaryButton from "../../../components/common/SecondaryButton";
import EmptyState from "../../../components/common/EmptyState";

import ConsultationTable from "../components/ConsultationTable";
import consultationService from "../services/consultationService";

export default function ConsultationListPage() {

    const navigate = useNavigate();
    const { id } = useParams();

    console.log("PATIENT ID:", id);

    const [consultations, setConsultations] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        if (id) {
            loadConsultations();
        }

    }, [id]);

    async function loadConsultations() {

        setLoading(true);

        try {

            const data =
                await consultationService.getPatientConsultations(id);

            setConsultations(data);

        }
        catch (error) {

            console.error(error);

        }
        finally {

            setLoading(false);

        }

    }

    async function handleDelete(id) {

        if (!window.confirm("Delete this consultation?")) return;

        try {

            await consultationService.deleteConsultation(id);

            loadConsultations();

        }
        catch (error) {

            console.error(error);

        }

    }

    return (

        <>

            <PageHeader
                title="Consultation History"
                subtitle="Patient consultation records"
            />

            <div className="action-bar">

                <PrimaryButton
                    onClick={() =>
                        navigate(`/doctor/consultations/new/${id}`)
                    }
                >
                    New Consultation
                </PrimaryButton>

                <SecondaryButton
                    onClick={() => navigate('/doctor/dashboard')}
                >
                    Back
                </SecondaryButton>

            </div>

            {
                loading ?

                    <p>Loading...</p>

                    :

                    consultations.length === 0 ?

                        <EmptyState
                            title="No consultations yet"
                            description="Create the patient's first consultation."
                        />

                        :

                        <ConsultationTable
                            consultations={consultations}
                            onView={(consultation) =>
                                navigate(`/doctor/consultations/${consultation.id}`)
                            }
                            onEdit={(consultation) =>
                                navigate(`/doctor/consultations/edit/${consultation.id}`)
                            }
                            onDelete={(consultation) =>
                                handleDelete(consultation.id)
                            }
                        />
            }

        </>

    );

}