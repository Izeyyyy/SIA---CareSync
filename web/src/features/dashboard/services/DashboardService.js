import api from "../../../services/api";

export const getDashboardStats = () => {
    return api.get("/dashboard/stats");
};