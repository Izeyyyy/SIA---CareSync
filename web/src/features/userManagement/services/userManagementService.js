import api from "../../../services/api";


export const getUsers = (search = "") => {
    return api.get("/users", {
        params: {
            search
        }
    });
};

export const updateUser = (id, user) => {

    return api.put(`/users/${id}`, user);

};

export const updateUserStatus = (id, active) => {

    return api.put(
        `/users/${id}/status`,
        null,
        {
            params:{
                active
            }
        }
    );

};

export const resetPassword = (id) => {
    return api.put(`/users/${id}/reset-password`);
};