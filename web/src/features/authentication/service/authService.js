import api from "../../../services/api";


export const changePassword = (newPassword) => {

    return api.put(
        "/auth/change-password",
        {
            newPassword
        }
    );

};