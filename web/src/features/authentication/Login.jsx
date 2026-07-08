import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Login() {

    const navigate = useNavigate();
    const location = useLocation();

    const [form, setForm] = useState({
        email: "",
        password: ""
    });
    const [error, setError] = useState("");
    const [successMessage, setSuccessMessage] = useState(location.state?.successMessage || "");

    useEffect(() => {
        if (!successMessage) return;

        const timer = setTimeout(() => setSuccessMessage(""), 4000);
        return () => clearTimeout(timer);
    }, [successMessage]);

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
        if (error) setError("");
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await api.post("/auth/login", {
                email: form.email,
                password: form.password
            });

            if (response.data) {
                const role = response.data.role?.toLowerCase();
                localStorage.setItem("user", JSON.stringify(response.data));

                const welcomeMessage = response.data.firstName
                    ? `Welcome back, ${response.data.firstName}!`
                    : "Welcome back!";

                if (role === "doctor") {
                    navigate("/doctor", { state: { successMessage: welcomeMessage } });
                } else if (role === "admin") {
                    navigate("/admin", { state: { successMessage: welcomeMessage } });
                } else {
                    navigate("/staff", { state: { successMessage: welcomeMessage } });
                }
            } else {
                setError("Invalid email or password.");
            }
        } catch (err) {
            console.error("Login failed:", err);
            setError("Login failed. Please check your credentials and try again.");
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <div className="auth-header">

                    <h1 className="auth-wordmark">CareSync</h1>

                    {/* optional pulse line (keep or remove) */}
                    <svg className="auth-pulse" viewBox="0 0 120 24">
                        <path d="M0 12 H20 L25 6 L35 18 L45 10 H120" />
                    </svg>

                    <p className="auth-subtitle">
                        Patient Management System
                    </p>

                </div>

                <form onSubmit={handleSubmit}>

                    <input
                        name="email"
                        type="email"
                        placeholder="Email"
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="password"
                        type="password"
                        placeholder="Password"
                        onChange={handleChange}
                        required
                    />

                    {error && (
                        <p style={{ color: "#dc3545", marginBottom: "0.75rem" }}>
                            {error}
                        </p>
                    )}

                    {successMessage && (
                        <p style={{ color: "#198754", marginBottom: "0.75rem" }}>
                            {successMessage}
                        </p>
                    )}

                    <button className="auth-btn" type="submit">
                        Login
                    </button>

                </form>

                {/* Register button */}
                <button
                    className="auth-link-btn"
                    onClick={() => navigate("/register")}
                >
                    Create an Account
                </button>

            </div>

        </div>
    );
}