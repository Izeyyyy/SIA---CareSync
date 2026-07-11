import api from "../../../services/api";


export const getUsers = () => {
    return api.get("/users");
};