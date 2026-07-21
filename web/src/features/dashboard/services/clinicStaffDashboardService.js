import api from "../../../services/api";


export async function getClinicStaffDashboard(){

    const response =
        await api.get("/staff/dashboard");

    return response.data;

}