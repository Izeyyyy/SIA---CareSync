import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // TEMP (we connect backend later)
        console.log(form);

        navigate("/admin"); // temporary redirect
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