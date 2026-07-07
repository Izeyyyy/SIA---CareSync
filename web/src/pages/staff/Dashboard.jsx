import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export default function StallDashboard() {

    const location = useLocation();
    const user = JSON.parse(localStorage.getItem("user") || "null");
    const [toast, setToast] = useState(location.state?.successMessage || "Welcome back!");

    useEffect(() => {
        if (!toast) return;

        const timer = setTimeout(() => setToast(""), 4000);
        return () => clearTimeout(timer);
    }, [toast]);

    return (
        <div style={{ padding: "2rem" }}>
            {toast && (
                <div style={{
                    position: "fixed",
                    top: "20px",
                    right: "20px",
                    background: "#198754",
                    color: "#fff",
                    padding: "0.9rem 1.1rem",
                    borderRadius: "10px",
                    boxShadow: "0 10px 25px rgba(0,0,0,0.15)",
                    zIndex: 1000
                }}>
                    {toast}
                </div>
            )}
            <h1>Staff Dashboard</h1>
            <p>Welcome {user?.firstName || "there"}</p>
            <p>Role: {user?.role || "staff"}</p>
        </div>
    );
}