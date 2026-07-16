import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import DashboardLayout from "../../../components/dashboard/DashboardLayout";
import PageHeader from "../../../components/dashboard/PageHeader";

import ConsultationForm from "../components/ConsultationForm";
import consultationService from "../services/consultationService";

export default function EditConsultationPage() {

    const navigate = useNavigate();

    const { id } = useParams();

    const [consultation, setConsultation] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadConsultation();

    }, []);

    async function loadConsultation() {

        try {

            const data =
                await consultationService.getConsultationById(id);

            setConsultation(data);

        }

        catch (error) {

            console.error(error);

            alert("Unable to load consultation.");

        }

        finally {

            setLoading(false);

        }

    }

    async function handleSubmit(formData) {

        try {

            await consultationService.updateConsultation(

                id,

                {

                    patientId: consultation.patientId,

                    doctorId: consultation.doctorId,

                    chiefComplaint: formData.chiefComplaint,

                    diagnosis: formData.diagnosis,

                    treatmentPlan: formData.treatmentPlan,

                    prescription: formData.prescription,

                    notes: formData.notes

                }

            );

            navigate(`/doctor/consultations/${id}`);

        }

        catch (error) {

            console.error(error);

            alert("Unable to update consultation.");

        }

    }

    if (loading) {

        return (

            <>

                <p>Loading...</p>

            </>


        );

    }

    return (

        <>

            <PageHeader

                title="Edit Consultation"

                subtitle="Update consultation record"

            />

            <ConsultationForm

                initialData={consultation}

                onSubmit={handleSubmit}

                onCancel={() => navigate(-1)}

            />

        </>

    );

}