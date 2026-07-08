import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Register() {

    const navigate = useNavigate();
    const location = useLocation();

    const [form, setForm] = useState({
        firstName: "",
        middleInitial: "",
        lastName: "",
        email: "",
        password: "",
        role: "staff"
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
            const response = await api.post("/auth/register", {
                firstName: form.firstName,
                middleInitial: form.middleInitial ? form.middleInitial[0] : "N",
                lastName: form.lastName,
                email: form.email,
                password: form.password,
                role: form.role
            });

            if (response.status === 201 || response.status === 200) {
                navigate("/login", {
                    state: { successMessage: "Account created successfully. Please sign in." }
                });
            }
        } catch (err) {
            console.error("Registration failed:", err);
            const message = err.response?.data?.message || "Registration failed. Please try again.";
            setError(message);
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-shell register-shell">

                <div className="auth-header">
                    <h1 className="auth-wordmark">CareSync</h1>
                    <p className="auth-subtitle">Create a new account</p>
                </div>

                <div className="auth-card">

                    <h2 className="auth-title">Register Account</h2>

                    <form className="auth-form" onSubmit={handleSubmit}>

                        {/* Row 1 */}
                        <div className="auth-row">
                            <input
                                name="firstName"
                                placeholder="First Name"
                                onChange={handleChange}
                                required
                            />

                            <input
                                name="middleInitial"
                                placeholder="M.I."
                                maxLength="1"
                                onChange={handleChange}
                            />
                        </div>

                        {/* Row 2 */}
                        <input
                            name="lastName"
                            placeholder="Last Name"
                            onChange={handleChange}
                            required
                        />

                        {/* Email */}
                        <input
                            name="email"
                            placeholder="Email"
                            type="email"
                            onChange={handleChange}
                            required
                        />

                        {/* Role */}
                        <select
                            name="role"
                            onChange={handleChange}
                            value={form.role}
                            required
                        >   
                            <option value="" disabled>
                                Select user role
                            </option>

                            <option value="staff">Clinic Staff</option>
                            <option value="doctor">Doctor</option>
                        </select>

                        {/* Password */}
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
                            Create Account
                        </button>

                    </form>

                </div>

                {/* Bottom link */}
                <div className="auth-footer-note">
                    Already have an account?{" "}
                    <button
                        type = "button"
                        className="auth-inline-link"
                        onClick={() => navigate("/login")}
                    >
                        Sign in
                    </button>
                </div>

            </div>

        </div>
    );
}