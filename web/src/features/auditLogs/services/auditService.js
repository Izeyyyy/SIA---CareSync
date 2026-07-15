import api from "../../../services/api";

export const getAuditLogs = async () => {
    const response = await api.get("/audit");
    return response.data;
};