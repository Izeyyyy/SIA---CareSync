import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./features/authentication/Login";
import Register from "./features/authentication/Register";
import AdminDashboard from "./features/dashboard/AdminDashboard";
import ProtectedRoute from "./components/auth/ProtectedRoute";




function App() {
    return (
        <BrowserRouter>
            <Routes>

                <Route path="/" element={<Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/admin/dashboard" element={
                    <ProtectedRoute role="admin">
                        <AdminDashboard />
                    </ProtectedRoute>
                } />

            </Routes>
        </BrowserRouter>
    );
}

export default App;