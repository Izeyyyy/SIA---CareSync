import { useNavigate, useParams } from "react-router-dom";

import DashboardLayout from "../../../components/dashboard/DashboardLayout";
import PageHeader from "../../../components/dashboard/PageHeader";

import ConsultationForm from "../components/ConsultationForm";

import consultationService from "../services/consultationService";

import { useAuth } from "../../../context/AuthContext";


export default function NewConsultationPage() {

    const navigate = useNavigate();

    const { patientId } = useParams();

    const { user } = useAuth();


    async function handleSubmit(formData) {

        try {

            await consultationService.createConsultation({

                patientId: Number(patientId),

                doctorId: user.id,

                chiefComplaint: formData.chiefComplaint,

                diagnosis: formData.diagnosis,

                treatmentPlan: formData.treatmentPlan,

                prescription: formData.prescription,

                notes: formData.notes

            });

            navigate(`/doctor/patients/${patientId}`);

        }
        catch (error) {

            console.error(error);

            alert("Unable to save consultation.");

        }

    }


    return (

        <>

            <PageHeader

                title="New Consultation"

                subtitle="Create a consultation record"

            />

            <ConsultationForm

                onSubmit={handleSubmit}

                onCancel = {() => navigate(-1)}

            />

        </>

    );

}