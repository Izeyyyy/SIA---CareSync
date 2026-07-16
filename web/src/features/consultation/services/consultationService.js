import api from "../../../services/api";

const consultationService = {

    getAllConsultations: async () => {
        const response = await api.get("/consultations");
        return response.data;
    },

    getConsultationById: async (id) => {
        const response = await api.get(`/consultations/${id}`);
        return response.data;
    },

    getPatientConsultations: async (patientId) => {
        const response = await api.get(`/consultations/patient/${patientId}`);
        return response.data;
    },

    getDoctorConsultations: async (doctorId) => {
        const response = await api.get(`/consultations/doctor/${doctorId}`);
        return response.data;
    },

    createConsultation: async (consultation) => {
        const response = await api.post("/consultations", consultation);
        return response.data;
    },

    updateConsultation: async (id, consultation) => {
        const response = await api.put(`/consultations/${id}`, consultation);
        return response.data;
    },

    deleteConsultation: async (id) => {
        await api.delete(`/consultations/${id}`);
    }

};

export default consultationService;