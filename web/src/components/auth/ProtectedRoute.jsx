import { Navigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function ProtectedRoute({

    children,

    role

}) {

    const { user, loading } = useAuth();

    // Not logged in

    if (loading) {
        return <div>Loading...</div>;
    }

    if (!user) {

        return <Navigate to="/login" replace />;

    }

    // Wrong role

    if (role && user.role.toLowerCase() !== role.toLowerCase()) {

        switch (user.role.toLowerCase()) {

            case "admin":
                return <Navigate to="/admin/dashboard" replace />;

            case "doctor":
                return <Navigate to="/doctor/dashboard" replace />;

            case "clinic staff":
            case "staff":
                return <Navigate to="/staff/dashboard" replace />;

            default:
                return <Navigate to="/login" replace />;
        }

    }

    return children;

}