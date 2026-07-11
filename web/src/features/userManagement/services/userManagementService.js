import api from "../../../services/api";


export const getUsers = () => {
    return api.get("/users");
};

export const updateUser = (id, user) => {

    return api.put(`/users/${id}`, user);

};