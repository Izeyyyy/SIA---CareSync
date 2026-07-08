import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./features/authentication/Login";
import Register from "./features/authentication/Register";

import AdminDashboard from "./pages/admin/Dashboard";
import DoctorDashboard from "./pages/doctor/Dashboard";
import StaffDashboard from "./pages/staff/Dashboard";


function App() {
    return (
        <BrowserRouter>
            <Routes>

                <Route path="/" element={<Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />

                <Route path="/admin" element={<AdminDashboard />} />
                <Route path="/doctor" element={<DoctorDashboard />} />
                <Route path="/staff" element={<StaffDashboard />} />

            </Routes>
        </BrowserRouter>
    );
}

export default App;