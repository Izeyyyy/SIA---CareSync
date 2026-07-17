import axios from "axios";


const api = axios.create({

    baseURL: "https://caresync-sia.onrender.com/api"

});


api.interceptors.request.use((config) => {

    // Don't attach JWT when logging in or registering
    if (
        config.url === "/auth/login" ||
        config.url === "/auth/register"
    ) {
        return config;
    }

    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});


export default api;