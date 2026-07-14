import api from "../../../services/api";

export const getPatients = () => {
    return api.get("/patients");
};

export const getPatient = (id) => {
    return api.get(`/patients/${id}`);
};

export const registerPatient = (patient) => {
    return api.post("/patients", patient);
};

export const updatePatient = (id, patient) => {
    return api.put(`/patients/${id}`, patient);
};

export const deletePatient = (id) => {
    return api.delete(`/patients/${id}`);
};