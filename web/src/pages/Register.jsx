import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Register() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        firstName: "",
        middleInitial: "",
        lastName: "",
        email: "",
        password: "",
        role: "staff"
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(form);
        navigate("/login");
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

                        <button className="auth-btn" type="submit">
                            Create Account
                        </button>

                    </form>

                </div>

                {/* Bottom link */}
                <div className="auth-footer-note">
                    Already have an account?{" "}
                    <button
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